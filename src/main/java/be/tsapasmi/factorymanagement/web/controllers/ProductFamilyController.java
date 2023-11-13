package be.tsapasmi.factorymanagement.web.controllers;

import be.tsapasmi.factorymanagement.bl.interfaces.ProductFamilyService;
import be.tsapasmi.factorymanagement.bl.interfaces.UserService;
import be.tsapasmi.factorymanagement.domain.entities.User;
import be.tsapasmi.factorymanagement.web.mappers.ProductFamilyMapper;
import be.tsapasmi.factorymanagement.web.models.dto.ProductFamilyDTO;
import be.tsapasmi.factorymanagement.web.models.form.ProductFamilyForm;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-family")
@AllArgsConstructor
public class ProductFamilyController {

    private final ProductFamilyService service;
    private final ProductFamilyMapper mapper;
    private final UserService userService;


    @GetMapping("/all")
    public ResponseEntity<List<ProductFamilyDTO>> getAllProductFamilies() {
        return ResponseEntity.ok(mapper.toDTO(service.getAll()));
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ProductFamilyDTO> getProductFamily(@PathVariable long id) {
        return ResponseEntity.ok(mapper.toDTO(service.getOne(id)));
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createProductFamily(@Valid @RequestBody ProductFamilyForm form) {

        service.create(mapper.toEntity(form));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<HttpStatus> updateProductFamily(@PathVariable long id, @Valid @RequestBody ProductFamilyForm form) {
        service.update(id, mapper.toEntity(form));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<HttpStatus> deleteProductFamily(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
