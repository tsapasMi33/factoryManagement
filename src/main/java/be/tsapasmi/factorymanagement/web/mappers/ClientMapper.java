package be.tsapasmi.factorymanagement.web.mappers;

import be.tsapasmi.factorymanagement.domain.entities.Client;
import be.tsapasmi.factorymanagement.web.models.dto.ClientDTO;
import be.tsapasmi.factorymanagement.web.models.form.ClientForm;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ClientMapper {

    ClientDTO toDTO(Client client);

    List<ClientDTO> toDTO(List<Client> clients);

    Client toEntity(ClientForm form);

}
