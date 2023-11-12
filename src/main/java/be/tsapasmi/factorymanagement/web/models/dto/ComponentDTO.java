package be.tsapasmi.factorymanagement.web.models.dto;

import be.tsapasmi.factorymanagement.domain.entities.Component;
import be.tsapasmi.factorymanagement.domain.enums.Material;

import java.io.Serializable;
/**
 * DTO for {@link Component}
 */
public record ComponentDTO(long id, String name, String type, Material material, Integer thickness, Integer length, Integer width, double price) implements Serializable {
}
