package be.tsapasmi.factorymanagement.web.models.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Shipment}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShipmentForm implements Serializable {
    private List<Long> packetIds;
}