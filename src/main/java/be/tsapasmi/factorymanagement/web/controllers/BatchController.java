package be.tsapasmi.factorymanagement.web.controllers;

import be.tsapasmi.factorymanagement.bl.interfaces.BatchService;
import be.tsapasmi.factorymanagement.domain.entities.Batch;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import be.tsapasmi.factorymanagement.web.mappers.BatchMapper;
import be.tsapasmi.factorymanagement.web.models.forms.BatchForm;
import be.tsapasmi.factorymanagement.web.models.dtos.BatchDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<BatchDto>> getBatches(
            @RequestParam int page,
            @RequestParam(required = false) Step currentStep,
            @RequestParam(required = false) Step nextStep) {
        Page<Batch> b = service.findAllByCriteria(page, currentStep,nextStep);
        System.out.println(b.getContent().size());
        return ResponseEntity.ok(service.findAllByCriteria(page, currentStep,nextStep)
                .map(mapper::toDto));
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<BatchDto> getBatch(@PathVariable long id) {
        return ResponseEntity.ok(mapper.toDto(service.getOne(id)));
    }

    @PostMapping("/put-in-production")
    public ResponseEntity<HttpStatus> createBatch(@Valid @RequestBody List<BatchForm> forms) {
        forms.forEach(batchForm -> service.create(mapper.toEntity(batchForm)));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<HttpStatus> deleteBatch(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{batchId:^[0-9]+$}/start")
    public ResponseEntity<BatchDto> startStep(@PathVariable Long batchId, @RequestParam Step step) {
        return ResponseEntity.ok(mapper.toDto(service.startStep(step, batchId)));
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
