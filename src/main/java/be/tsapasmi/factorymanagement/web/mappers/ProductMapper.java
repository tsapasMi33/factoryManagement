package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.bl.interfaces.*;
import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.entities.ProductVariant;
import be.tsapasmi.factorymanagement.web.models.dto.ProductDTO;
import be.tsapasmi.factorymanagement.web.models.form.ProductForm;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
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

    public abstract ProductDTO toDTO(Product product);

    public abstract List<ProductDTO> toDTO(List<Product> product);

    @Mapping(target = "variant", source = "variantId", qualifiedByName = "mapProductVariant")
    @Mapping(target = "currentStep", expression = "java(Step.ENCODED)")
    public abstract Product toEntity(ProductForm form);

    @Named("mapProductVariant")
    protected ProductVariant mapProductVariant(Long variantId) {
        return productVariantService.getOne(variantId);
    }

}
