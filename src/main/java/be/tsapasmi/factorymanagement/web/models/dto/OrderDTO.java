package be.tsapasmi.factorymanagement.web.models.dto;

import be.tsapasmi.factorymanagement.domain.enums.DeliveryPreference;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Order}
 */
public record OrderDTO(Long id, LocalDate orderDate, LocalDate plannedDeliveryDate,
                       DeliveryPreference deliveryPreference, List<ProductDto> products,
                       ClientDTO client) implements Serializable {
    /**
     * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Product}
     */
    public record ProductDto(Long id, String comments, ProductVariantDTO variant) implements Serializable {
    }
}
