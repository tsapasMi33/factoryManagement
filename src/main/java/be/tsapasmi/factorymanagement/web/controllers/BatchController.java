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
    public ResponseEntity<List<BatchDTO>> getBatches(@RequestParam(required = false) Step currentStep,
                                                     @RequestParam(required = false) Step nextStep) {
        return ResponseEntity.ok(mapper.toDTO(service.findAllByCriteria(currentStep,nextStep)));
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<BatchDTO> getBatch(@PathVariable long id) {
        return ResponseEntity.ok(mapper.toDTO(service.getOne(id)));
    }

    @PostMapping("/put-in-production")
    public ResponseEntity<HttpStatus> createBatch(@Valid @RequestBody List<BatchForm> forms) {
        forms.forEach(batchForm -> service.create(mapper.toEntity(batchForm)));
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

    @PatchMapping("/{batchId:^[0-9]+$}/start")
    public ResponseEntity<BatchDTO> startStep(@PathVariable Long batchId, @RequestParam Step step) {
        return ResponseEntity.ok(mapper.toDTO(service.startStep(step, batchId)));
    }

    @PatchMapping("/{batchId:^[0-9]+$}/pause")
    public ResponseEntity<HttpStatus> pauseStep(@PathVariable Long batchId, @RequestParam Step step) {
        service.pauseStep(step, batchId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{batchId:^[0-9]+$}/finish")
    public ResponseEntity<HttpStatus> finishStep(@PathVariable Long batchId, @RequestParam Step step) {
        service.finishStep(step, batchId);
        return ResponseEntity.ok().build();
    }

}
