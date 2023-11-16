package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.bl.interfaces.PacketService;
import be.tsapasmi.factorymanagement.domain.entities.Packet;
import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.entities.Shipment;
import be.tsapasmi.factorymanagement.web.models.dto.PacketDTO;
import be.tsapasmi.factorymanagement.web.models.dto.ShipmentDTO;
import be.tsapasmi.factorymanagement.web.models.form.ShipmentForm;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(uses = {PacketMapper.class, PacketService.class})
public abstract class ShipmentMapper {
    protected PacketService packetService;
    protected PacketMapper packetMapper;

    @Named("shipmentToShipmentDTO")
    @Mapping(target = "packets", source = "packets", qualifiedByName = "mapPacketDTOs")
    public abstract ShipmentDTO toDTO(Shipment shipment);

    @IterableMapping(qualifiedByName = "shipmentToShipmentDTO")
    public abstract List<ShipmentDTO> toDTO(List<Shipment> shipments);

    @Mapping(target = "packets", source = "packetIds", qualifiedByName = "mapPackets")
    public abstract Shipment toEntity(ShipmentForm form);

    @Named("mapPacketDTOs")
    protected List<PacketDTO> mapPacketDTOs(List<Packet> packets) {
        return packets.stream().map(packet -> packetMapper.toDTO(packet)).toList();
    }


    @Named("mapPackets")
    protected List<Packet> mapPackets(List<Long> packets) {
        return packets.stream()
                .map(packetId -> packetService.getOne(packetId))
                .toList();
    }
}
