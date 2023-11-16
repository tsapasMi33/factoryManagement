package be.tsapasmi.factorymanagement.web.models.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Shipment}
 */
public record ShipmentDTO(LocalDateTime createdDate, Long id, List<PacketDTO> packets) implements Serializable {
}