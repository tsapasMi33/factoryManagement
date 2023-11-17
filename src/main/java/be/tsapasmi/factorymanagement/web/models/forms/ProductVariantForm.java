package be.tsapasmi.factorymanagement.web.models.forms;

import be.tsapasmi.factorymanagement.domain.enums.Material;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.ProductVariant}
 */
public record ProductVariantForm(@NotNull Material material, @NotNull @NotBlank String variantIdentifier, Integer width,
                                 Integer length, Integer height, double price, String description,
                                 @NotNull ProductVariantForm.ProductFamilyIdForm productFamily,
                                 @NotNull @Size(min = 1) List<ComponentIdForm> components) implements Serializable {
    /**
     * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.ProductFamily}
     */
    public record ProductFamilyIdForm(@Positive Long id) implements Serializable {
    }

    /**
     * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Component}
     */
    public record ComponentIdForm(@Positive Long id) implements Serializable {
    }
}
