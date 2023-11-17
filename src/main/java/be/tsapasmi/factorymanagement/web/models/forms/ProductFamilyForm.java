package be.tsapasmi.factorymanagement.web.models.forms;

import be.tsapasmi.factorymanagement.domain.enums.Step;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.ProductFamily}
 */
public record ProductFamilyForm(@NotNull @NotBlank String name,
                                @NotNull List<Step> productionPath) implements Serializable {
}
