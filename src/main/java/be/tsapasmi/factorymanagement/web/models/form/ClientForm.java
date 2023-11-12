package be.tsapasmi.factorymanagement.web.models.form;

import be.tsapasmi.factorymanagement.domain.entities.Address;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ClientForm {

    @NotBlank
    private String name;

    @NotBlank
    private String companyType;

    @NotNull
    private Address address;

    @PositiveOrZero
    @Max(100)
    private int discountPercentage;
}
