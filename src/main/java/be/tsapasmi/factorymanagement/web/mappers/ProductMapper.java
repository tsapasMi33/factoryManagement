package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.bl.interfaces.*;
import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.entities.ProductVariant;
import be.tsapasmi.factorymanagement.web.models.dto.ProductDTO;
import be.tsapasmi.factorymanagement.web.models.form.BatchForm;
import be.tsapasmi.factorymanagement.web.models.form.ProductForm;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(uses = {ProductVariantService.class, OrderService.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class ProductMapper {

    protected ProductVariantService productVariantService;
    protected OrderService orderService;

    @Autowired
    public void setProductVariantService(ProductVariantService productVariantService) {
        this.productVariantService = productVariantService;
    }
    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Named("productToProductDTO")
    @Mapping(source = "packet.code", target = "packetCode")
    @Mapping(source = "packet.id", target = "packetId")
    @Mapping(source = "batch.code", target = "batchCode")
    @Mapping(source = "batch.id", target = "batchId")
    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "order.plannedDeliveryDate", target = "orderPlannedDeliveryDate")
    @Mapping(source = "order.client.id", target = "orderClientId")
    @Mapping(source = "order.client.name", target = "orderClientName")
    public abstract ProductDTO toDTO(Product product);

    @IterableMapping(qualifiedByName = "productToProductDTO")
    public abstract List<ProductDTO> toDTO(List<Product> product);

    @Mapping(target = "variant", source = "variantId", qualifiedByName = "mapProductVariant")
    public abstract Product toEntity(ProductForm form);


    @Named("mapProductVariant")
    protected ProductVariant mapProductVariant(Long variantId) {
        return productVariantService.getOne(variantId);
    }

}
