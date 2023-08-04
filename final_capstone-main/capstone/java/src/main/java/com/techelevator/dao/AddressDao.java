package com.techelevator.dao;

import com.techelevator.model.Address;

import java.util.List;

public interface AddressDao {

    //get an address by id
    Address getAddressById(int id);

    //get a list of addresses
    List<Address>getAddresses();

    //get address by client_id
    Address getAddressByClientId(int clientId);


    //create an address
    Address createAddress(Address address);

}
