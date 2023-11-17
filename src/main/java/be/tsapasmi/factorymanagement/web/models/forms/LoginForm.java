package be.tsapasmi.factorymanagement.web.models.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginForm(@NotNull @Size(min = 4, max = 10) @NotBlank String username,
                        @NotNull @Size(min = 6, max = 6) @NotBlank String password) {
}
