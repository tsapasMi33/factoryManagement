package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.web.models.dtos.ProductDto;
import be.tsapasmi.factorymanagement.web.models.forms.ProductForm;
import org.mapstruct.*;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductVariantMapper.class, ComponentMapper.class})
public interface ProductMapper {


    @Mapping(target = "batchId", source = "batch.id")
    @Mapping(target = "batchCode", source = "batch.code")
    @Mapping(target = "packetId", source = "packet.id")
    @Mapping(target = "packetCode", source = "packet.code")
    ProductDto toDto(Product product);

    Product toEntity(ProductForm productForm);

}
