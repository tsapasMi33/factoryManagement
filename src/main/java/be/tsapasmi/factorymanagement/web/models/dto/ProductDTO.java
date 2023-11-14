package be.tsapasmi.factorymanagement.web.models.dto;

import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.enums.Step;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Product}
 */
public record ProductDTO(Long id, String comments, ProductVariantDTO variant, Long orderId,
                         LocalDate orderPlannedDeliveryDate, Long orderClientId,
                         String orderClientName, Step currentStep, Long batchId, String batchCode, Long packetId,
                         String packetCode) implements Serializable {
}
