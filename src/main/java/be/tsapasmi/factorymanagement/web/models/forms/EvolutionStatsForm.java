package be.tsapasmi.factorymanagement.web.models.forms;

import java.io.Serializable;

public record EvolutionStatsForm(boolean comparison, int periodSpan) implements Serializable {
}
