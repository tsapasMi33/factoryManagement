package be.tsapasmi.factorymanagement.web.controllers;

import be.tsapasmi.factorymanagement.bl.interfaces.OrderService;
import be.tsapasmi.factorymanagement.web.mappers.OrderMapper;
import be.tsapasmi.factorymanagement.web.models.dtos.OrderDto;
import be.tsapasmi.factorymanagement.web.models.forms.OrderForm;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService service;
    private final OrderMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok(mapper.toDto(service.getAll()));
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable long id) {
        return ResponseEntity.ok(mapper.toDto(service.getOne(id)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<HttpStatus> createOrder(@Valid @RequestBody OrderForm form) {
        service.create(mapper.toEntity(form));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<HttpStatus> updateOrder(@PathVariable long id, @Valid @RequestBody OrderForm form) {
        service.update(id, mapper.toEntity(form));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
