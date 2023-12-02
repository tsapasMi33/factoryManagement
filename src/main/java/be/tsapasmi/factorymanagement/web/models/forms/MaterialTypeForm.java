package be.tsapasmi.factorymanagement.web.models.forms;

import be.tsapasmi.factorymanagement.domain.enums.Material;
import be.tsapasmi.factorymanagement.domain.enums.PricingMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serializable;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.MaterialType}
 */
public record MaterialTypeForm(@NotNull @NotBlank String name, @NotNull Material material,
                               @NotNull PricingMethod pricingMethod, @Positive Double basePrice,
                               boolean requiresCutting, boolean requiresBending, boolean hasThickness,
                               boolean hasLength, boolean hasWidth) implements Serializable {
}
