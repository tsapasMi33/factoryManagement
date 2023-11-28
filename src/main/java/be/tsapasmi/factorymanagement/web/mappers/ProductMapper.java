package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.web.models.dtos.ProductDto;
import be.tsapasmi.factorymanagement.web.models.forms.ProductForm;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductVariantMapper.class})
public interface ProductMapper {

    @Named("toDto")
    @Mapping(target = "batchId", source = "batch.id")
    @Mapping(target = "batchCode", source = "batch.code")
    @Mapping(target = "packetId", source = "packet.id")
    @Mapping(target = "packetCode", source = "packet.code")
    ProductDto toDto(Product product);
    @IterableMapping(qualifiedByName = "toDto")
    List<ProductDto> toDto(List<Product> products);

    Product toEntity(ProductForm productForm);

}
