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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ProductMapper mapper;

    //all
    @GetMapping("/all")
    public ResponseEntity<Page<ProductDto>> getAllProducts(
            @RequestParam int page,
            @RequestParam(required = false)Step currentStep,
            @RequestParam(required = false)Step nextStep,
            @RequestParam(required = false)Long productFamilyId,
            @RequestParam(required = false)Long batchId,
            @RequestParam(required = false)Long clientId,
            @RequestParam(required = false)LocalDate deliveryDate,
            @RequestParam(required = false)LocalDate orderDate,
            @RequestParam(required = false)Long packetId,
            @RequestParam(required = false)String productVariantCode
    ) {
        return ResponseEntity.ok(service.findAllByCriteria(page, currentStep, nextStep,productFamilyId, batchId, clientId, deliveryDate,orderDate,packetId,productVariantCode)
                        .map(mapper::toDto));
    }

    // all
    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable long id) {
        return ResponseEntity.ok(mapper.toDto(service.getOne(id)));
    }

    // all
    @GetMapping("code/{orderCode:^[0-9]+$}/{productCode:^[0-9]+[.][0-9]{2}$}")
    public ResponseEntity<ProductDto> getProductByCode(@PathVariable String orderCode,@PathVariable String productCode) {
        return ResponseEntity.ok(mapper.toDto(service.getByCode(orderCode,productCode)));
    }

    @PreAuthorize("hasRole('ADMIN')")
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

    @PreAuthorize("hasAnyRole('ADMIN','FINISHER')")
    @PatchMapping("/{productId:^[0-9]+$}/start-finish")
    public ResponseEntity<ProductDto> startFinishing(@PathVariable Long productId) {
        return ResponseEntity.ok(mapper.toDto(service.startStep(Step.FINISHED, productId)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','FINISHER')")
    @PatchMapping("/{productId:^[0-9]+$}/pause-finish")
    public ResponseEntity<ProductDto> pauseFinishing(@PathVariable Long productId) {
        return ResponseEntity.ok(mapper.toDto(service.pauseStep(Step.FINISHED, productId)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','FINISHER')")
    @PatchMapping("/{productId:^[0-9]+$}/finish-finish")
    public ResponseEntity<ProductDto> finishFinishing(@PathVariable Long productId) {
        return ResponseEntity.ok(mapper.toDto(service.finishStep(Step.FINISHED, productId)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/archive")
    public ResponseEntity<HttpStatus> archiveAll() {
        service.archiveAll();
        return ResponseEntity.ok().build();
    }
}
