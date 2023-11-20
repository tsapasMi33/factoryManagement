package be.tsapasmi.factorymanagement.web.controllers;

import be.tsapasmi.factorymanagement.bl.interfaces.ProductService;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import be.tsapasmi.factorymanagement.web.mappers.ProductMapper;
import be.tsapasmi.factorymanagement.web.models.dtos.ProductDto;
import be.tsapasmi.factorymanagement.web.models.forms.ProductForm;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ProductMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<Page<ProductDto>> getAllProducts(
            @RequestParam int page,
            @RequestParam(required = false)Step currentStep,
            @RequestParam(required = false)Step nextStep,
            @RequestParam(required = false)Long productFamilyId,
            @RequestParam(required = false)Long batchId,
            @RequestParam(required = false)Long clientId,
            @RequestParam(required = false)LocalDate deliveryDate,
            @RequestParam(required = false) LocalDate orderDate,
            @RequestParam(required = false)Long packetId,
            @RequestParam(required = false)String productVariantCode
    ) {
        return ResponseEntity.ok(service.findAllByCriteria(page, currentStep, nextStep,productFamilyId, batchId, clientId, deliveryDate,orderDate,packetId,productVariantCode)
                        .map(mapper::toDto));
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable long id) {
        return ResponseEntity.ok(mapper.toDto(service.getOne(id)));
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
    public ResponseEntity<ProductDto> startStep(@PathVariable Long productId, @RequestParam Step step) {
        return ResponseEntity.ok(mapper.toDto(service.startStep(step, productId)));
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
