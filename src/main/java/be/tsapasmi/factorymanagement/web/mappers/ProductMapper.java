package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.web.models.dtos.ProductDto;
import be.tsapasmi.factorymanagement.web.models.forms.ProductForm;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductVariantMapper.class})
public interface ProductMapper {

    @Named("toDto")
    ProductDto toDto(Product product);
    @IterableMapping(qualifiedByName = "toDto")
    List<ProductDto> toDto(List<Product> products);

    Product toEntity(ProductForm productForm);

}
