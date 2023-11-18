package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.domain.entities.Client;
import be.tsapasmi.factorymanagement.web.models.dtos.ClientDto;
import be.tsapasmi.factorymanagement.web.models.forms.ClientForm;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {

    @Named("toDto")
    ClientDto toDto(Client client);

    @IterableMapping(qualifiedByName = "toDto")
    List<ClientDto> toDto(List<Client> clients);

    Client toEntity(ClientForm clientForm);
}
