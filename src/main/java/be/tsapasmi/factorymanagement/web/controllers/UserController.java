package be.tsapasmi.factorymanagement.web.controllers;

import be.tsapasmi.factorymanagement.bl.interfaces.UserService;
import be.tsapasmi.factorymanagement.domain.entities.User;
import be.tsapasmi.factorymanagement.security.jwt.JWTProvider;
import be.tsapasmi.factorymanagement.web.mappers.UserMapper;
import be.tsapasmi.factorymanagement.web.models.dto.AuthDTO;
import be.tsapasmi.factorymanagement.web.models.dto.UserDTO;
import be.tsapasmi.factorymanagement.web.models.form.LoginForm;
import be.tsapasmi.factorymanagement.web.models.form.UserCreationForm;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private final UserService service;
    private final JWTProvider jwtProvider;
    private final UserMapper mapper;


    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllClients() {
        return ResponseEntity.ok(mapper.toDTO(service.getAll()));
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<UserDTO> getUser(@PathVariable long id) {
        return ResponseEntity.ok(mapper.toDTO(service.getOne(id)));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDTO> login(@Valid @RequestBody LoginForm form) {
        User user = service.login(form.getUsername(), form.getPassword());
        String token = jwtProvider.generateToken(user);

        return ResponseEntity.ok(new AuthDTO(form.getUsername(), token, user.getRole().name()));
    }

    @PostMapping("/create-user")
    public ResponseEntity<HttpStatus> createUser(@Valid @RequestBody UserCreationForm form) {
        service.create(mapper.toEntity(form));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<HttpStatus> updateUser(@PathVariable long id, @Valid @RequestBody UserCreationForm form) {
        service.update(id, mapper.toEntity(form));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @PatchMapping("/{id:^[0-9]+$}/disable")
    public ResponseEntity<HttpStatus> disableUser(@PathVariable long id) {
        service.disableUser(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id:^[0-9]+$}/enable")
    public ResponseEntity<HttpStatus> enableUser(@PathVariable long id) {
        service.enableUser(id);
        return ResponseEntity.ok().build();
    }
}
