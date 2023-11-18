package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.domain.entities.ProductFamily;
import be.tsapasmi.factorymanagement.web.models.dtos.ProductFamilyDto;
import be.tsapasmi.factorymanagement.web.models.forms.ProductFamilyForm;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductFamilyMapper {

    @Named("toDto")
    ProductFamilyDto toDto(ProductFamily productFamily);

    @IterableMapping(qualifiedByName = "toDto")
    List<ProductFamilyDto> toDto(List<ProductFamily> productFamilies);

    ProductFamily toEntity(ProductFamilyForm productFamilyForm);
}
