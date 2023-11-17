package be.tsapasmi.factorymanagement.web.models.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Packet}
 */
public record PacketDto(LocalDateTime createdDate, Long id, String code, String clientName,
                        List<ProductDtoInPacketDto> products) implements Serializable {
    /**
     * DTO for {@link be.tsapasmi.factorymanagement.domain.entities.Product}
     */
    public record ProductDtoInPacketDto(Long id, String comments, ProductVariantDto variant) implements Serializable {
    }
}
