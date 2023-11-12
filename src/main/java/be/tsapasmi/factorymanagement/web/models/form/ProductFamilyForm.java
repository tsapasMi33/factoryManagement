package be.tsapasmi.factorymanagement.web.models.form;

import be.tsapasmi.factorymanagement.domain.entities.ProductFamily;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for {@link ProductFamily}
 */
@Getter
@Setter
public class ProductFamilyForm {

    @NotBlank
    private String name;
}
