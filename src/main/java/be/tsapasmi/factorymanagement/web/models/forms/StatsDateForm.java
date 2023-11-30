package be.tsapasmi.factorymanagement.web.models.forms;

import java.io.Serializable;
import java.time.LocalDate;

public record StatsDateForm(LocalDate startDate, LocalDate endDate) implements Serializable {
}
