package be.tsapasmi.factorymanagement.bl.interfaces;

import be.tsapasmi.factorymanagement.domain.entities.Client;

import java.util.List;


public interface ClientService extends BaseService<Client,Long> {

    public List<Client> getAllActiveClients();
}
