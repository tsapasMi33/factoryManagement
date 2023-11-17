package be.tsapasmi.factorymanagement.web.models.forms;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Shipment}
 */
public record ShipmentForm(@NotNull @Size(min = 1) List<PacketIdForm> packets) implements Serializable {
    /**
     * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Packet}
     */
    public record PacketIdForm(@Positive Long id) implements Serializable {
    }
}
