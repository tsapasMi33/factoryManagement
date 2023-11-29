package be.tsapasmi.factorymanagement.web.controllers;

import be.tsapasmi.factorymanagement.bl.interfaces.ProductFamilyService;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import be.tsapasmi.factorymanagement.web.mappers.ProductFamilyMapper;
import be.tsapasmi.factorymanagement.web.models.dtos.ProductFamilyDto;
import be.tsapasmi.factorymanagement.web.models.forms.ProductFamilyForm;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-family")
@AllArgsConstructor
public class ProductFamilyController {

    private final ProductFamilyService service;
    private final ProductFamilyMapper mapper;


    @GetMapping("/all")
    public ResponseEntity<List<ProductFamilyDto>> getAllProductFamilies() {
        return ResponseEntity.ok(mapper.toDto(service.getAll()));
    }

    @GetMapping("/all-active")
    public ResponseEntity<List<ProductFamilyDto>> getAllActive(@RequestParam(required = false) Step productsAtStep,
                                                               @RequestParam(required = false)Step productsAtNextStep) {
        return ResponseEntity.ok(mapper.toDto(service.getAllActive(productsAtStep, productsAtNextStep)));
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ProductFamilyDto> getProductFamily(@PathVariable long id) {
        return ResponseEntity.ok(mapper.toDto(service.getOne(id)));
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
