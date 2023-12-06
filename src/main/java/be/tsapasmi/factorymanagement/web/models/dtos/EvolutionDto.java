package be.tsapasmi.factorymanagement.web.models.dtos;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

public record EvolutionDto(Map<String, Map<String, Integer>> data,Map<String, Map<String, Integer>> previousData, List<String> labels) implements Serializable {
}
