package be.tsapasmi.factorymanagement.web.models.dto;

import be.tsapasmi.factorymanagement.domain.entities.ProductVariant;
import be.tsapasmi.factorymanagement.domain.enums.Material;
import be.tsapasmi.factorymanagement.domain.enums.Step;

import java.util.List;

/**
 * DTO for {@link ProductVariant}
 */
public record ProductVariantDTO(long id,
                                List<Step> productionPath,
                                Material material,
                                String variantIdentifier,
                                Integer width,
                                Integer length,
                                Integer height,
                                String code,
                                double price,
                                String description,
                                ProductFamilyDTO productFamily,
                                List<ComponentDTO> components) {
}
