package be.tsapasmi.factorymanagement.web.models.form;

import be.tsapasmi.factorymanagement.domain.enums.Material;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ComponentForm {

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @NotNull
    private Material material;

    @Positive
    private Integer thickness;

    @Positive
    private Integer length;

    @Positive
    private Integer width;

    @Min(1)
    private int qty;

    @Positive
    private double price;
}
