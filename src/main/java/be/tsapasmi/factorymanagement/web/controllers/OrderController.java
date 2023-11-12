package be.tsapasmi.factorymanagement.web.controllers;

import be.tsapasmi.factorymanagement.bl.interfaces.OrderService;
import be.tsapasmi.factorymanagement.bl.interfaces.ProductStepService;
import be.tsapasmi.factorymanagement.web.mappers.OrderMapper;
import be.tsapasmi.factorymanagement.web.models.dto.OrderDTO;
import be.tsapasmi.factorymanagement.web.models.form.OrderForm;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService service;
    private final OrderMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(mapper.toDTO(service.getAll()));
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<OrderDTO> getProductVariant(@PathVariable long id) {
        return ResponseEntity.ok(mapper.toDTO(service.getOne(id)));
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createProductVariant(@Valid @RequestBody OrderForm form, Authentication auth) {
        service.create(mapper.toEntity(form));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<HttpStatus> updateProductVariant(@PathVariable long id, @Valid @RequestBody OrderForm form) {
        service.update(id, mapper.toEntity(form));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<HttpStatus> deleteProductVariant(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
