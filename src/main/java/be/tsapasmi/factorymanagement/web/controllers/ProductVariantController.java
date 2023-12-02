package be.tsapasmi.factorymanagement.web.controllers;

import be.tsapasmi.factorymanagement.bl.interfaces.ProductVariantService;
import be.tsapasmi.factorymanagement.web.mappers.ProductVariantMapper;
import be.tsapasmi.factorymanagement.web.models.dtos.ProductVariantDto;
import be.tsapasmi.factorymanagement.web.models.forms.ProductVariantForm;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-variant")
@AllArgsConstructor
public class ProductVariantController {

    private final ProductVariantService service;
    private final ProductVariantMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<List<ProductVariantDto>> getAllProductVariants() {
        return ResponseEntity.ok(mapper.toDto(service.getAll()));
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ProductVariantDto> getProductVariant(@PathVariable long id) {
        return ResponseEntity.ok(mapper.toDto(service.getOne(id)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<HttpStatus> createProductVariant(@Valid @RequestBody ProductVariantForm form) {
        service.create(mapper.toEntity(form));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<HttpStatus> updateProductVariant(@PathVariable long id, @Valid @RequestBody ProductVariantForm form) {
        service.update(id, mapper.toEntity(form));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<HttpStatus> deleteProductVariant(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
