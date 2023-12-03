package be.tsapasmi.factorymanagement.web.models.dtos;

import be.tsapasmi.factorymanagement.domain.enums.Material;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.ProductVariant}
 */
public record ProductVariantDto(Long id, Material material, Integer width, Integer length, Integer height, String code,
                                double price, String description, ProductFamilyDto productFamily,
                                List<ComponentDto> components) implements Serializable {
}
