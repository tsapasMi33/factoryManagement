package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.domain.entities.Packet;
import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.web.models.dtos.PacketDto;
import be.tsapasmi.factorymanagement.web.models.forms.PacketForm;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PacketMapper {

    @Named("toDto")
    @Mapping(target = "clientName", source = "products",qualifiedByName = "mapClientName")
    PacketDto toDto(Packet packet);

    @Named("mapClientName")
    default String mapClientName(List<Product> products) {
        return products.get(0).getOrder().getClient().getName();
    }

    @IterableMapping(qualifiedByName = "toDto")
    List<PacketDto> toDto(List<Packet> packets);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Packet partialUpdate(PacketDto packetDto, @MappingTarget Packet packet);

    Packet toEntity(PacketForm packetForm);

}
