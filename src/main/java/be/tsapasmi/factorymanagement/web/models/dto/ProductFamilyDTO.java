package be.tsapasmi.factorymanagement.web.models.dto;

import be.tsapasmi.factorymanagement.domain.entities.ProductFamily;

import java.io.Serializable;

/**
 * DTO for {@link ProductFamily}
 */
public record ProductFamilyDTO(long id, String name) implements Serializable {
}
