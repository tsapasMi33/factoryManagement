package be.tsapasmi.factorymanagement.web.models.dto;

import be.tsapasmi.factorymanagement.domain.enums.DeliveryPreference;
import be.tsapasmi.factorymanagement.domain.entities.Order;
import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.enums.Step;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link Order}
 */
public record OrderDTO(Long id, LocalDate orderDate, LocalDate plannedDeliveryDate,
                       DeliveryPreference deliveryPreference, List<ProductDto> products,
                       ClientDTO client) implements Serializable {
    /**
     * DTO for {@link Product}
     */
    public record ProductDto(Long id, String comments, ProductVariantDTO variant, Step currentStep) implements Serializable {
    }
}
