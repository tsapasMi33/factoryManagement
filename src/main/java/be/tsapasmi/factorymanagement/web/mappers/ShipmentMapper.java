package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.domain.entities.Shipment;
import be.tsapasmi.factorymanagement.web.models.dtos.ShipmentDto;
import be.tsapasmi.factorymanagement.web.models.forms.ShipmentForm;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {PacketMapper.class})
public interface ShipmentMapper {

    @Named("toDto")
    ShipmentDto toDto(Shipment shipment);

    @IterableMapping(qualifiedByName = "toDto")
    List<ShipmentDto> toDto(List<Shipment> shipments);

    Shipment toEntity(ShipmentForm shipmentForm);
}
