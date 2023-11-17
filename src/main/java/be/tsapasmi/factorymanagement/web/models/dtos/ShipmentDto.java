package be.tsapasmi.factorymanagement.web.models.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Shipment}
 */
public record ShipmentDto(LocalDateTime createdDate, Long id, List<PacketDto> packets) implements Serializable {
}
