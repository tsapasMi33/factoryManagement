package be.tsapasmi.factorymanagement.web.models.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductFamilyForm {

    @NotBlank
    private String name;
}
