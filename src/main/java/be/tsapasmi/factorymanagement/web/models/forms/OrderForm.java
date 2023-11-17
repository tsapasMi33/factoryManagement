package be.tsapasmi.factorymanagement.web.models.forms;

import be.tsapasmi.factorymanagement.domain.enums.DeliveryPreference;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Order}
 */
public record OrderForm(@Future LocalDate plannedDeliveryDate, @NotNull DeliveryPreference deliveryPreference,
                        @NotNull OrderForm.ClientIdForm client, List<ProductForm> products) implements Serializable {
    /**
     * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Client}
     */
    public record ClientIdForm(@Positive Long id) implements Serializable {
    }
}
