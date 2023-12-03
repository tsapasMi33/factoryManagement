package be.tsapasmi.factorymanagement.web.models.dtos;

import be.tsapasmi.factorymanagement.domain.enums.Material;
import be.tsapasmi.factorymanagement.domain.enums.PricingMethod;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.MaterialType}
 */
public record MaterialTypeDto(LocalDateTime lastModifiedDate, Long id, String name, Material material,
                              PricingMethod pricingMethod, Double basePrice, boolean hasThickness,
                              boolean hasLength, boolean hasWidth) implements Serializable {
}
