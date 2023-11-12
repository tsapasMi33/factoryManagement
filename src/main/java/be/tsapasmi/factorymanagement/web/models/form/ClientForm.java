package be.tsapasmi.factorymanagement.web.models.form;

import be.tsapasmi.factorymanagement.domain.entities.Address;
import be.tsapasmi.factorymanagement.domain.entities.Client;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for {@link Client}
 */
@Getter
@Setter
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
