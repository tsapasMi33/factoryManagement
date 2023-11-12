package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.domain.entities.ProductFamily;
import be.tsapasmi.factorymanagement.web.models.dto.ProductFamilyDTO;
import be.tsapasmi.factorymanagement.web.models.form.ProductFamilyForm;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProductFamilyMapper {

    ProductFamilyDTO toDTO(ProductFamily productFamily);

    List<ProductFamilyDTO> toDTO(List<ProductFamily> productFamilies);

    ProductFamily toEntity(ProductFamilyForm form);
}
