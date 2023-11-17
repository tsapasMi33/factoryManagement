package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.domain.entities.ProductVariant;
import be.tsapasmi.factorymanagement.web.models.dtos.ProductVariantDto;
import be.tsapasmi.factorymanagement.web.models.forms.ProductVariantForm;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductFamilyMapper.class, ComponentMapper.class})
public interface ProductVariantMapper {

    @Named("toDto")
    ProductVariantDto toDto(ProductVariant productVariant);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductVariant partialUpdate(ProductVariantDto productVariantDto, @MappingTarget ProductVariant productVariant);

    @IterableMapping(qualifiedByName = "toDto")
    List<ProductVariantDto> toDto(List<ProductVariant> productVariants);

    ProductVariant toEntity(ProductVariantForm productVariantForm);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductVariant partialUpdate(ProductVariantForm productVariantForm, @MappingTarget ProductVariant productVariant);
}
