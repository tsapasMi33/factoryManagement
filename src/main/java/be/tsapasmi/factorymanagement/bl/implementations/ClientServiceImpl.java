package be.tsapasmi.factorymanagement.bl.implementations;

import be.tsapasmi.factorymanagement.bl.interfaces.ClientService;
import be.tsapasmi.factorymanagement.dal.ClientRepository;
import be.tsapasmi.factorymanagement.domain.entities.Client;
import be.tsapasmi.factorymanagement.domain.enums.Step;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Getter
public class ClientServiceImpl extends BaseServiceImpl<Client,Long, ClientRepository> implements ClientService {


    public ClientServiceImpl(ClientRepository repo) {
        super(repo, Client.class);
    }

    public List<Client> getAllActiveClients(Step productsAtStep, Step productsAtNextStep){
        if (productsAtStep != null) {
            return repository.getAllActiveWithProductsAtStep(productsAtStep);
        }
        if (productsAtNextStep != null) {
            return repository.getAllActiveWithProductsAtNextStep(productsAtNextStep);
        }
        return repository.getAllActive();
    }


}
