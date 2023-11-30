package be.tsapasmi.factorymanagement.bl.interfaces;

import be.tsapasmi.factorymanagement.web.models.dtos.StatsDto;

import java.time.LocalDate;

public interface StatsService {
    StatsDto getProductionStatsForPeriod(LocalDate startDate, LocalDate endDate);
}
