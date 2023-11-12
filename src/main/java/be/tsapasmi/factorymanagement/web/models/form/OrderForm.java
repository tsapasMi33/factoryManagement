package be.tsapasmi.factorymanagement.web.models.form;

import be.tsapasmi.factorymanagement.domain.enums.DeliveryPreference;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Order}
 */
@Getter
@Setter
public class OrderForm implements Serializable {
    @FutureOrPresent
    private LocalDate plannedDeliveryDate;
    @NotNull
    private DeliveryPreference deliveryPreference;
    private List<ProductInOrderForm> products;
    @NotNull
    private ClientForm client;

    /**
     * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Product}
     */
    @Getter
    @Setter
    public static class ProductInOrderForm implements Serializable {
        private Long id;
        private String comments;
        private ProductVariantDto variantId;

        /**
         * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.ProductVariant}
         */
        @Getter
        @Setter
        public static class ProductVariantDto implements Serializable {
            private Long id;
        }
    }
}
