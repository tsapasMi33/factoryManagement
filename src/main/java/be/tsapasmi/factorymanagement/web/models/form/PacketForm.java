package be.tsapasmi.factorymanagement.web.models.form;

import be.tsapasmi.factorymanagement.domain.entities.Packet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Packet}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PacketForm implements Serializable {
    private List<Long> productIds;
}