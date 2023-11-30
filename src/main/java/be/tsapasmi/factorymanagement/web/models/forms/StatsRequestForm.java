package be.tsapasmi.factorymanagement.web.models.forms;

import be.tsapasmi.factorymanagement.domain.enums.Step;

import java.io.Serializable;
import java.time.LocalDate;

public record StatsRequestForm(LocalDate startDate, LocalDate endDate, Step step, String username) implements Serializable {
}
