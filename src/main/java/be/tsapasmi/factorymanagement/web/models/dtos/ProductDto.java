package be.tsapasmi.factorymanagement.web.models.dtos;

import be.tsapasmi.factorymanagement.domain.enums.DeliveryPreference;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Product}
 */
public record ProductDto(Long id, String comments, Step currentStep, ProductVariantDto variant, Long orderId,
                         Long batchId, String batchCode, Long packetId, String packetCode,
                         OrderDtoInProductDto order, String code, List<ProductStepDto> steps) implements Serializable {
    /**
     * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Order}
     */
    public record OrderDtoInProductDto(LocalDateTime createdDate, Long id, LocalDate plannedDeliveryDate, String code,
                                       ProductDto.OrderDtoInProductDto.ClientDto client) implements Serializable {
        /**
         * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Client}
         */
        public record ClientDto(Long id, String name) implements Serializable {
        }
    }
}
