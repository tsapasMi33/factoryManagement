package be.tsapasmi.factorymanagement.web.models.form;

import be.tsapasmi.factorymanagement.domain.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.User}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreationForm implements Serializable {
    @Length(min = 4, max = 12)
    private String username;
    @NotNull
    @NotBlank
    @Length(min = 6, max = 6)
    private String password;
    @NotNull
    private Role role;
    @PositiveOrZero
    private Double costPerMinute;
}
