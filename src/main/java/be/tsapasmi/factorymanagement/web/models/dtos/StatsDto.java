package be.tsapasmi.factorymanagement.web.models.dtos;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

public record StatsDto(Map<String,Integer> stats, LocalDate startDate, LocalDate endDate) implements Serializable {
}
