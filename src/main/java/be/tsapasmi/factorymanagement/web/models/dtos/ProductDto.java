package be.tsapasmi.factorymanagement.web.models.dtos;

import be.tsapasmi.factorymanagement.domain.enums.Step;

import java.io.Serializable;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Product}
 */
public record ProductDto(Long id, String comments, Step currentStep, ProductVariantDto variant, Long orderId,
                         Long batchId, String batchCode, Long packetId, String packetCode) implements Serializable {
}
