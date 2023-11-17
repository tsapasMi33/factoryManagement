package be.tsapasmi.factorymanagement.web.models.dtos;

import be.tsapasmi.factorymanagement.domain.enums.Step;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Batch}
 */
public record BatchDto(Long id, String code, List<ProductDtoInBatch> products, Step currentStep) implements Serializable {
    /**
     * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Product}
     */
    public record ProductDtoInBatch(Long id, String comments, be.tsapasmi.factorymanagement.web.models.dtos.ProductVariantDto variant) implements Serializable {
    }
}
