package be.tsapasmi.factorymanagement.web.controllers;

import be.tsapasmi.factorymanagement.bl.interfaces.AuthService;
import be.tsapasmi.factorymanagement.domain.entities.User;
import be.tsapasmi.factorymanagement.security.jwt.JWTProvider;
import be.tsapasmi.factorymanagement.web.models.dto.AuthDTO;
import be.tsapasmi.factorymanagement.web.models.form.LoginForm;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@AllArgsConstructor
public class AuthController {

    private final AuthService service;
    private final JWTProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<AuthDTO> login(@Valid @RequestBody LoginForm form) {
        User user = service.login(form.getUsername(), form.getPassword());
        String token = jwtProvider.generateToken(user);

        return ResponseEntity.ok(new AuthDTO(form.getUsername(), token, user.getRole().name()));
    }
}
