package be.tsapasmi.factorymanagement.web.models.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Product}
 */
public record ProductForm(@NotNull @NotBlank String comments,
                          @NotNull ProductForm.ProductVariantIdForm variant) implements Serializable {
    /**
     * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.ProductVariant}
     */
    public record ProductVariantIdForm(@Positive Long id) implements Serializable {
    }
}
