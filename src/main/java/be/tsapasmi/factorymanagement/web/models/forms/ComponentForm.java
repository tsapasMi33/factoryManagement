package be.tsapasmi.factorymanagement.web.models.forms;

import be.tsapasmi.factorymanagement.domain.enums.Material;
import be.tsapasmi.factorymanagement.domain.enums.MaterialType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Component}
 */
public record ComponentForm(@NotNull @NotBlank String name, @NotNull MaterialType type, @NotNull Material material,
                            Integer thickness, Integer length, Integer width, double price) implements Serializable {
}
