package be.tsapasmi.factorymanagement.web.models.form;

import be.tsapasmi.factorymanagement.domain.entities.Component;
import be.tsapasmi.factorymanagement.domain.enums.Material;
import be.tsapasmi.factorymanagement.domain.enums.MaterialType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for {@link Component}
 */
@Getter
@Setter
public class ComponentForm {

    @NotBlank
    private String name;

    private MaterialType type;

    @NotNull
    private Material material;

    @Positive
    private Integer thickness;

    @Positive
    private Integer length;

    @Positive
    private Integer width;

    @Positive
    private double price;
}
