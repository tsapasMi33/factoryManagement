package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.domain.entities.Order;
import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.entities.ProductVariant;
import be.tsapasmi.factorymanagement.web.models.dtos.OrderDto;
import be.tsapasmi.factorymanagement.web.models.forms.OrderForm;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ClientMapper.class})
public interface OrderMapper {

    @Named("toDto")
    OrderDto toDto(Order order);

    @IterableMapping(qualifiedByName = "toDto")
    List<OrderDto> toDto(List<Order> orders);

    @Mapping(source = "products", target = "products", qualifiedByName = "mapProducts")
    Order toEntity(OrderForm orderForm);

    @Named("mapProducts")
    default List<Product> mapProducts(List<OrderForm.ProductFormInOrderForm> productForms) {
        List<Product> products = new ArrayList<>();
        int pCount = 1;
        for (OrderForm.ProductFormInOrderForm productForm : productForms) {
            if (productForm.quantity() == 1) {
                Product product = new Product();
                product.setCode(String.valueOf(pCount++) + ".00");
                product.setComments(productForm.comments());
                ProductVariant pv = new ProductVariant();
                pv.setId(productForm.variant().id());
                product.setVariant(pv);
                products.add(product);
            } else {
                for (int i = 1; i <= productForm.quantity(); i++) {
                    Product product = new Product();
                    product.setCode(String.valueOf(pCount) + (i < 10 ? ".0" + i : "." + i));
                    product.setComments(productForm.comments());
                    ProductVariant pv = new ProductVariant();
                    pv.setId(productForm.variant().id());
                    product.setVariant(pv);
                    products.add(product);
                }
                pCount++;
            }
        }
        return products;
    }
}
