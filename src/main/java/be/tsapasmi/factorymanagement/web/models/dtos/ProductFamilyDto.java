package be.tsapasmi.factorymanagement.web.models.dtos;

import be.tsapasmi.factorymanagement.domain.enums.Step;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.ProductFamily}
 */
public record ProductFamilyDto(Long id, String name, List<Step> productionPath) implements Serializable {
}
