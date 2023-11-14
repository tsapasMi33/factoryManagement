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

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ProductMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts(@RequestParam(required = false)Step step,
                                                           @RequestParam(required = false)Long batchId,
                                                           @RequestParam(required = false)Long packetId,
                                                           @RequestParam(required = false)Long productFamilyId
                                                           ) {
        return ResponseEntity.ok(mapper.toDTO(service.findAllByCriteria(step,batchId,packetId,productFamilyId)));
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

}
