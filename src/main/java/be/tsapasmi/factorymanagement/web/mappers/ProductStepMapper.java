package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.domain.entities.ProductStep;
import be.tsapasmi.factorymanagement.web.models.dto.ProductStepDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper
public interface ProductStepMapper {

    ProductStepDTO toDTO(ProductStep productStep);

    List<ProductStepDTO> toDTO(List<ProductStep> productSteps);

    ProductStep toEntity(ProductStepDTO productStepDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductStep partialUpdate(ProductStepDTO productStepDTO, @MappingTarget ProductStep productStep);
}
