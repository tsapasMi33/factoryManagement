package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.domain.entities.ProductStep;
import be.tsapasmi.factorymanagement.web.models.dto.ProductStepDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProductStepMapper {

    ProductStepDTO toDTO(ProductStep productStep);

    List<ProductStepDTO> toDTO(List<ProductStep> productSteps);
}
