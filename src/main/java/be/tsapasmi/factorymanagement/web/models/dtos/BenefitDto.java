package be.tsapasmi.factorymanagement.web.models.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public record BenefitDto(Map<String, Double> catalogPrice, Map<String, Double> sellPrice, Map<String, Double> productionCost, List<String> labels) implements Serializable {
}
