package be.tsapasmi.factorymanagement.web.models.forms;

import be.tsapasmi.factorymanagement.domain.enums.DeliveryPreference;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Order}
 */
public record OrderForm(@Future LocalDate plannedDeliveryDate, @NotNull DeliveryPreference deliveryPreference,
                        @NotNull OrderForm.ClientIdForm client,
                        List<ProductFormInOrderForm> products) implements Serializable {
    /**
     * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Client}
     */
    public record ClientIdForm(@Positive Long id) implements Serializable {
    }

    /**
     * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Product}
     */

    public record ProductFormInOrderForm(Long id, String comments, int quantity,
                                         OrderForm.ProductFormInOrderForm.ProductVariantIdForm variant) implements Serializable {
        /**
         * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.ProductVariant}
         */

        public record ProductVariantIdForm(@Positive Long id) implements Serializable {
        }
    }
}
