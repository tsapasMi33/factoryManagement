package be.tsapasmi.factorymanagement.bl.interfaces;

import be.tsapasmi.factorymanagement.domain.enums.Step;
import be.tsapasmi.factorymanagement.web.models.dtos.StatsDto;

import java.time.LocalDate;

public interface StatsService {
    StatsDto getProductionStats(LocalDate startDate, LocalDate endDate);

    StatsDto getStepStats(LocalDate startDate, LocalDate endDate, Step step);
}
