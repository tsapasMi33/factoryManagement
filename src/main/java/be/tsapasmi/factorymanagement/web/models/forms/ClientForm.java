package be.tsapasmi.factorymanagement.web.models.forms;

import be.tsapasmi.factorymanagement.domain.entities.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Client}
 */
public record ClientForm(@NotBlank String name, String companyType, @NotNull Address address,
                         @Range(min = 0, max = 60) int discountPercentage) implements Serializable {
}
