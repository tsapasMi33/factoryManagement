package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.bl.interfaces.*;
import be.tsapasmi.factorymanagement.domain.entities.Client;
import be.tsapasmi.factorymanagement.domain.entities.Order;
import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.entities.ProductVariant;
import be.tsapasmi.factorymanagement.web.models.dto.OrderDTO;
import be.tsapasmi.factorymanagement.web.models.form.OrderForm;
import be.tsapasmi.factorymanagement.web.models.form.ProductForm;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(uses = {ProductMapper.class, ClientService.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class OrderMapper {

    protected ProductMapper productMapper;
    protected ClientService clientService;

    @Autowired
    public void setProductMapper(ProductMapper productMapper){
        this.productMapper = productMapper;
    }
    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    public abstract OrderDTO toDTO(Order order);

    public abstract List<OrderDTO> toDTO(List<Order> orders);

    @Mapping(target = "orderDate", expression = "java(LocalDate.now())")
    @Mapping(target = "products", source = "products", qualifiedByName = "mapProducts")
    @Mapping(target = "client", source = "clientId", qualifiedByName = "mapClient")
    public abstract Order toEntity(OrderForm form);

    @Named("mapProducts")
    protected List<Product> mapProducts(List<ProductForm> products) {
        return products.stream()
                .map(productForm -> productMapper.toEntity(productForm))
                .toList();
    }
    @Named("mapClient")
    protected Client mapClient(long clientId) {
        return clientService.getOne(clientId);
    }

    @AfterMapping
    protected void setOrderReferenceInProducts(@MappingTarget Order order) {
        order.getProducts().forEach(product -> product.setOrder(order));
    }
}
