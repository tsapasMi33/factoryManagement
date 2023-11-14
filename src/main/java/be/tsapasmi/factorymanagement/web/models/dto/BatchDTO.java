package be.tsapasmi.factorymanagement.web.models.dto;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Batch}
 */
public record BatchDTO(Long id, String code, List<ProductDTO> products) implements Serializable {
}