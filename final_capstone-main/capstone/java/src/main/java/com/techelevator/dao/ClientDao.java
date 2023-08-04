package com.techelevator.dao;

import com.techelevator.model.Client;

import java.util.List;

public interface ClientDao {


    //get a client by id
    Client getClientById(int id);

    //get a list of clients (admin)
    List<Client> getClients();

    //create a client
    boolean createClient(Client client);

    //update a client
    Client updateClient(Client client);

    //delete a client
    boolean deleteClient(Client client);

    //delete a client by ID
    boolean deleteClientById(int id);

}
