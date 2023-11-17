package be.tsapasmi.factorymanagement.web.controllers;

import be.tsapasmi.factorymanagement.bl.interfaces.ShipmentService;
import be.tsapasmi.factorymanagement.web.mappers.ShipmentMapper;
import be.tsapasmi.factorymanagement.web.models.dtos.ShipmentDto;
import be.tsapasmi.factorymanagement.web.models.forms.ShipmentForm;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shipment")
@AllArgsConstructor
public class ShipmentController {

    private final ShipmentService service;
    private final ShipmentMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<List<ShipmentDto>> getShipments() {
        return ResponseEntity.ok(mapper.toDto(service.getAll()));
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ShipmentDto> getShipment(@PathVariable long id) {
        return ResponseEntity.ok(mapper.toDto(service.getOne(id)));
    }

    @PostMapping("/send")
    public ResponseEntity<HttpStatus> createShipment(@Valid @RequestBody ShipmentForm form) {
        service.create(mapper.toEntity(form));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<HttpStatus> updateShipment(@PathVariable long id, @Valid @RequestBody ShipmentForm form) {
        service.update(id, mapper.toEntity(form));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<HttpStatus> deleteShipment(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
