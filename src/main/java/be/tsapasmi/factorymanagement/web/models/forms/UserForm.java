package be.tsapasmi.factorymanagement.web.models.forms;

import be.tsapasmi.factorymanagement.domain.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.User}
 */
public record UserForm(@NotNull @Size(min = 4, max = 10) @NotBlank String username,
                       @NotNull @Size(min = 6, max = 6) @NotBlank String password, @NotNull Role role,
                       @PositiveOrZero Double costPerMinute) implements Serializable {
}
