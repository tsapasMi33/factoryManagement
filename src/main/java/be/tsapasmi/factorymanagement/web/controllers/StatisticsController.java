package be.tsapasmi.factorymanagement.web.controllers;

import be.tsapasmi.factorymanagement.bl.interfaces.StatsService;
import be.tsapasmi.factorymanagement.web.models.dtos.StatsDto;
import be.tsapasmi.factorymanagement.web.models.forms.StatsDateForm;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/stats")
@AllArgsConstructor
public class StatisticsController {

    private final StatsService service;

    @PostMapping("/production")
    public ResponseEntity<StatsDto> getStats(@RequestBody StatsDateForm form) {
        return ResponseEntity.ok(service.getProductionStatsForPeriod(form.startDate(), form.endDate()));
    }
}
