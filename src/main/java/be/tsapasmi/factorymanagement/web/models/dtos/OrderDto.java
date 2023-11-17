package be.tsapasmi.factorymanagement.web.models.dtos;

import be.tsapasmi.factorymanagement.domain.enums.DeliveryPreference;
import be.tsapasmi.factorymanagement.domain.enums.Step;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Order}
 */
public record OrderDto(LocalDateTime createdDate, Long id, LocalDate plannedDeliveryDate,
                       DeliveryPreference deliveryPreference, ClientDto client, List<ProductDtoInOrder> products) implements Serializable {
    /**
     * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Product}
     */
    public record ProductDtoInOrder(LocalDateTime createdDate, Long id, String comments, Step currentStep, be.tsapasmi.factorymanagement.web.models.dtos.ProductVariantDto variant) implements Serializable {
    }
}
