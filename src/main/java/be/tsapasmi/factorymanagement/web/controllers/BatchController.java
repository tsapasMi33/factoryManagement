package be.tsapasmi.factorymanagement.web.controllers;

import be.tsapasmi.factorymanagement.bl.interfaces.BatchService;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import be.tsapasmi.factorymanagement.web.mappers.BatchMapper;
import be.tsapasmi.factorymanagement.web.models.forms.BatchForm;
import be.tsapasmi.factorymanagement.web.models.dtos.BatchDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
        return ResponseEntity.ok(service.findAllByCriteria(page, currentStep,nextStep)
                .map(mapper::toDto));
    }

    @GetMapping("all-active")
    public ResponseEntity<List<BatchDto>> getAllActive() {
        return ResponseEntity.ok(service.getAllActive().stream().map(mapper::toDto).toList());
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<BatchDto> getBatch(@PathVariable long id) {
        return ResponseEntity.ok(mapper.toDto(service.getOne(id)));
    }

    @GetMapping("/code/{code:^[0-9]+$}")
    public ResponseEntity<BatchDto> getBatchByCode(@PathVariable String code) {
        return ResponseEntity.ok(mapper.toDto(service.getByCode(code)));
    }

    @PreAuthorize("hasRole('ADMIN')")
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

    @PreAuthorize("hasAnyRole('ADMIN','CUTTER')")
    @PatchMapping("/{batchId:^[0-9]+$}/start-cut")
    public ResponseEntity<BatchDto> startCut(@PathVariable Long batchId) {
        return ResponseEntity.ok(mapper.toDto(service.startStep(Step.CUT, batchId)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','CUTTER')")
    @PatchMapping("/{batchId:^[0-9]+$}/pause-cut")
    public ResponseEntity<BatchDto> pauseCut(@PathVariable Long batchId) {
        return ResponseEntity.ok(mapper.toDto(service.pauseStep(Step.CUT, batchId)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','CUTTER')")
    @PatchMapping("/{batchId:^[0-9]+$}/finish-cut")
    public ResponseEntity<BatchDto> finishCut(@PathVariable Long batchId) {
        return ResponseEntity.ok(mapper.toDto(service.finishStep(Step.CUT, batchId)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','BENDER')")
    @PatchMapping("/{batchId:^[0-9]+$}/start-bend")
    public ResponseEntity<BatchDto> startBend(@PathVariable Long batchId) {
        return ResponseEntity.ok(mapper.toDto(service.startStep(Step.BENT, batchId)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','BENDER')")
    @PatchMapping("/{batchId:^[0-9]+$}/pause-bend")
    public ResponseEntity<BatchDto> pauseBend(@PathVariable Long batchId) {
        return ResponseEntity.ok(mapper.toDto(service.pauseStep(Step.BENT, batchId)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','BENDER')")
    @PatchMapping("/{batchId:^[0-9]+$}/finish-bend")
    public ResponseEntity<BatchDto> finishBend(@PathVariable Long batchId) {
        return ResponseEntity.ok(mapper.toDto(service.finishStep(Step.BENT, batchId)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','COMBINER')")
    @PatchMapping("/{batchId:^[0-9]+$}/start-combine")
    public ResponseEntity<BatchDto> startCombine(@PathVariable Long batchId) {
        return ResponseEntity.ok(mapper.toDto(service.startStep(Step.COMBINED, batchId)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','COMBINER')")
    @PatchMapping("/{batchId:^[0-9]+$}/pause-combine")
    public ResponseEntity<BatchDto> pauseCombine(@PathVariable Long batchId) {
        return ResponseEntity.ok(mapper.toDto(service.pauseStep(Step.COMBINED, batchId)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','COMBINER')")
    @PatchMapping("/{batchId:^[0-9]+$}/finish-combine")
    public ResponseEntity<BatchDto> finishCombine(@PathVariable Long batchId) {
        return ResponseEntity.ok(mapper.toDto(service.finishStep(Step.COMBINED, batchId)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','WELDER')")
    @PatchMapping("/{batchId:^[0-9]+$}/start-weld")
    public ResponseEntity<BatchDto> startWeld(@PathVariable Long batchId) {
        return ResponseEntity.ok(mapper.toDto(service.startStep(Step.WELDED, batchId)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','WELDER')")
    @PatchMapping("/{batchId:^[0-9]+$}/pause-weld")
    public ResponseEntity<BatchDto> pauseWeld(@PathVariable Long batchId) {
        return ResponseEntity.ok(mapper.toDto(service.pauseStep(Step.WELDED, batchId)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','WELDER')")
    @PatchMapping("/{batchId:^[0-9]+$}/finish-weld")
    public ResponseEntity<BatchDto> finishWeld(@PathVariable Long batchId) {
        return ResponseEntity.ok(mapper.toDto(service.finishStep(Step.WELDED, batchId)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','ASSEMBLER')")
    @PatchMapping("/{batchId:^[0-9]+$}/start-assemble")
    public ResponseEntity<BatchDto> startAssemble(@PathVariable Long batchId) {
        return ResponseEntity.ok(mapper.toDto(service.startStep(Step.ASSEMBLED, batchId)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','ASSEMBLER')")
    @PatchMapping("/{batchId:^[0-9]+$}/pause-assemble")
    public ResponseEntity<BatchDto> pauseAssemble(@PathVariable Long batchId) {
        return ResponseEntity.ok(mapper.toDto(service.pauseStep(Step.ASSEMBLED, batchId)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','ASSEMBLER')")
    @PatchMapping("/{batchId:^[0-9]+$}/finish-assemble")
    public ResponseEntity<BatchDto> finishAssemble(@PathVariable Long batchId) {
        return ResponseEntity.ok(mapper.toDto(service.finishStep(Step.ASSEMBLED, batchId)));
    }

}
