package be.tsapasmi.factorymanagement.web.controllers;

import be.tsapasmi.factorymanagement.bl.interfaces.ProductService;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import be.tsapasmi.factorymanagement.web.mappers.ProductMapper;
import be.tsapasmi.factorymanagement.web.models.dto.ProductDTO;
import be.tsapasmi.factorymanagement.web.models.form.ProductForm;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ProductMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts(@RequestParam(required = false)Step currentStep,
                                                           @RequestParam(required = false)Step nextStep,
                                                           @RequestParam(required = false)Long productFamilyId
                                                           ) {
        return ResponseEntity.ok(mapper.toDTO(service.findAllByCriteria(currentStep, nextStep,productFamilyId)));
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable long id) {
        return ResponseEntity.ok(mapper.toDTO(service.getOne(id)));
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<HttpStatus> updateProduct(@PathVariable long id, @Valid @RequestBody ProductForm form) {
        service.update(id, mapper.toEntity(form));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{productId:^[0-9]+$}/start")
    public ResponseEntity<ProductDTO> startStep(@PathVariable Long productId, @RequestParam Step step) {
        return ResponseEntity.ok(mapper.toDTO(service.startStep(step, productId)));
    }

    @PatchMapping("/{productId:^[0-9]+$}/pause")
    public ResponseEntity<HttpStatus> pauseStep(@PathVariable Long productId, @RequestParam Step step) {
        service.pauseStep(step, productId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{productId:^[0-9]+$}/finish")
    public ResponseEntity<HttpStatus> finishStep(@PathVariable Long productId, @RequestParam Step step) {
        service.finishStep(step, productId);
        return ResponseEntity.ok().build();
    }
}
