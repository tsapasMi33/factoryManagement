package be.tsapasmi.factorymanagement.web.models.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
