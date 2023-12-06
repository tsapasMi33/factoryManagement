package be.tsapasmi.factorymanagement.web.controllers;

import be.tsapasmi.factorymanagement.bl.interfaces.StatsService;
import be.tsapasmi.factorymanagement.web.models.dtos.EvolutionDto;
import be.tsapasmi.factorymanagement.web.models.dtos.StatsDto;
import be.tsapasmi.factorymanagement.web.models.forms.EvolutionStatsForm;
import be.tsapasmi.factorymanagement.web.models.forms.StatsRequestForm;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stats")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class StatisticsController {

    private final StatsService service;

    @PostMapping("/production")
    public ResponseEntity<StatsDto> getStats(@RequestBody StatsRequestForm form) {
        return ResponseEntity.ok(service.getProductionStats(form.startDate(), form.endDate()));
    }

    @PostMapping("/production/step")
    public ResponseEntity<StatsDto> getStatsForStep(@RequestBody StatsRequestForm form) {
        return ResponseEntity.ok(service.getStepStats(form.startDate(), form.endDate(), form.step()));
    }

    @PostMapping("/production/step/user")
    public ResponseEntity<StatsDto> getStatsForUser(@RequestBody StatsRequestForm form) {
        return ResponseEntity.ok(service.getUserStatsForStep(form.startDate(), form.endDate(), form.step(), form.username()));
    }

    @PostMapping("/evolution")
    public ResponseEntity<EvolutionDto> getEvolutionStats(@RequestBody EvolutionStatsForm form) {
        return ResponseEntity.ok(service.getEvolutionStats(form.comparison(), form.periodSpan()));
    }

    @GetMapping("/workload")
    public ResponseEntity<StatsDto> getWorkload() {
        return ResponseEntity.ok(service.getWorkload());
    }
}
