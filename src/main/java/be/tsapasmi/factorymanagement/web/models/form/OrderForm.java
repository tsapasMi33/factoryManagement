package be.tsapasmi.factorymanagement.web.models.form;

import be.tsapasmi.factorymanagement.domain.enums.DeliveryPreference;
import be.tsapasmi.factorymanagement.domain.entities.Order;
import jakarta.validation.constraints.Future;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link Order}
 */
@Getter
@Setter
public class OrderForm implements Serializable {
    @Future
    private LocalDate plannedDeliveryDate;
    private DeliveryPreference deliveryPreference;
    private List<ProductForm> products;
    private Long clientId;
}
