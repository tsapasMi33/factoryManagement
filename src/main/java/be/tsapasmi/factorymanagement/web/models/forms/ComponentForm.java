package be.tsapasmi.factorymanagement.web.models.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Component}
 */
public record ComponentForm(@NotNull @NotBlank String name, Integer thickness, Integer length, Integer width,
                            MaterialTypeIdForm type, double price, boolean requiresCutting, boolean requiresBending) implements Serializable {
    /**
     * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.MaterialType}
     */
    public record MaterialTypeIdForm(Long id) implements Serializable {
    }
}
