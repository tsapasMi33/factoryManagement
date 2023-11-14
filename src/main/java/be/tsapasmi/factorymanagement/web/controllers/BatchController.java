package be.tsapasmi.factorymanagement.web.controllers;

import be.tsapasmi.factorymanagement.bl.interfaces.BatchService;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import be.tsapasmi.factorymanagement.web.mappers.BatchMapper;
import be.tsapasmi.factorymanagement.web.models.dto.BatchDTO;
import be.tsapasmi.factorymanagement.web.models.form.BatchForm;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/batch")
@AllArgsConstructor
public class BatchController {

    private final BatchService service;
    private final BatchMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<List<BatchDTO>> getBatches(@RequestParam(required = false) Step step) {
        return ResponseEntity.ok(mapper.toDTO(service.findAllByCriteria(step)));
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<BatchDTO> getBatch(@PathVariable long id) {
        return ResponseEntity.ok(mapper.toDTO(service.getOne(id)));
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createBatch(@Valid @RequestBody BatchForm form) {
        service.create(mapper.toEntity(form));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<HttpStatus> updateBatch(@PathVariable long id, @Valid @RequestBody BatchForm form) {
        service.update(id, mapper.toEntity(form));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<HttpStatus> deleteBatch(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/to-cut")
    public ResponseEntity<List<BatchDTO>> getBatches() {
        return ResponseEntity.ok(mapper.toDTO());
    }

    @GetMapping("/to-bend")
    public ResponseEntity<List<BatchDTO>> getBatches() {
        return ResponseEntity.ok(mapper.toDTO());
    }

    @GetMapping("/to-combine")
    public ResponseEntity<List<BatchDTO>> getBatches() {
        return ResponseEntity.ok(mapper.toDTO());
    }

    @GetMapping("/to-weld")
    public ResponseEntity<List<BatchDTO>> getBatches() {
        return ResponseEntity.ok(mapper.toDTO());
    }
}
