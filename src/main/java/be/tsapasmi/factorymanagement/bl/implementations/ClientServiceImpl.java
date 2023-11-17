package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.ClientService;
import be.tsapasmi.factorymanagement.dal.ClientRepository;
import be.tsapasmi.factorymanagement.domain.entities.Client;
import lombok.Getter;
import org.springframework.stereotype.Service;


@Service
@Getter
public class ClientServiceImpl extends BaseServiceImpl<Client,Long, ClientRepository> implements ClientService {


    public ClientServiceImpl(ClientRepository repo) {
        super(repo, Client.class);
    }


}
