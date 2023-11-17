package be.tsapasmi.factorymanagement.web.models.dtos;

import be.tsapasmi.factorymanagement.domain.enums.Material;
import be.tsapasmi.factorymanagement.domain.enums.MaterialType;

import java.io.Serializable;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Component}
 */
public record ComponentDto(Long id, String name, MaterialType type, Material material, Integer thickness,
                           Integer length, Integer width, double price) implements Serializable {
}
