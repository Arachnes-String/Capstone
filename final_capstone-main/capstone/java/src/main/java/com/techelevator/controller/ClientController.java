package com.techelevator.controller;

import com.techelevator.dao.AddressDao;
import com.techelevator.dao.BinDao;
import com.techelevator.dao.ClientDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.*;
import com.techelevator.service.PickUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
public class ClientController {

    //to-do: add authorization req to these routes

    private ClientDao clientDao;
    private UserDao userDao;
    private AddressDao addressDao;
    private BinDao binDao;
    private PickUpService pickUpService;

    @Autowired
    public ClientController(ClientDao clientDao, UserDao userDao, AddressDao addressDao, BinDao binDao, PickUpService pickUpService) {
        this.clientDao = clientDao;
        this.userDao = userDao;
        this.addressDao = addressDao;
        this.binDao = binDao;
        this.pickUpService = pickUpService;
    }


    //create a new "client"
    @RequestMapping(path = "/clients", method = RequestMethod.POST)
    public boolean createNewClient(@RequestBody ClientDTO body) {

        //* grab the userID using the data incl in the ClientDTO....
        int userId = userDao.findIdByUsername(body.getUsername());
        body.setUserId(userId);

        //use address data from ClientDTO to create a row in the addresses database...
        Address address = addressDao.createAddress(new Address(body));

        //grab that id and set it...
        body.setAddressId(address.getAddressId());

        //use our constructor to turn our DTO into a Client object...
        Client client = new Client(body);

        //send it to our db and then create a row in the bins table for our client...
        if (clientDao.createClient(client)) {

            binDao.createBinForClientByClientID(client.getClientId());
            return true;

            //if it does not successfully create a row, return false
        } else return false;

    }

    @GetMapping(path = "/clients/all")
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        //Variables for method
        List<Client> allClients = new ArrayList<>();
        List<ClientDTO> listToReturn = new ArrayList<>();

        try {
            allClients = clientDao.getClients();
            //Loop through and turn clients into client objects
            for (Client client : allClients) {
                ClientDTO currentClientDTO = pickUpService.convertToClientDTO(client);
                listToReturn.add(currentClientDTO);
            }
            return ResponseEntity.ok(listToReturn);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //get a client by id (maybe for an account details page?)
    @RequestMapping(path = "/clients/{id}", method = RequestMethod.GET)
    public Client getClientById(@PathVariable int id) {
        return clientDao.getClientById(id);
    }

    //update a client by id
    @PutMapping("/clients/{id}")
    public ResponseEntity<?> updateClientById(@RequestBody Client client, @PathVariable int id) {
        try {
            client.setClientId(id);
            Client updatedClient = clientDao.updateClient(client);
            return ResponseEntity.ok(updatedClient);
        } catch (Exception e) {
            String errorMessage = "Failed to update client with ID: " + id;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    //get a client's address by client id
    @RequestMapping(path = "/clients/{id}/address", method = RequestMethod.GET)
    public Address getClientAddressByClientID(@PathVariable int id) {
        Client client = clientDao.getClientById(id);

        if (client == null) {
            throw new UserNotFoundException();
        }
        return addressDao.getAddressById(client.getAddressId());

    }

    //get a client's bin row (stats)
    @RequestMapping(path = "/clients/{id}/stats", method = RequestMethod.GET)
    public Bin getBinByClientID(@PathVariable int id) {
        return binDao.getBinByClientId(id);
    }

    //update a client's bin row (stats)
    @RequestMapping(path = "/clients/{id}/stats", method = RequestMethod.PUT)
    public Bin updateBinByClientID(@PathVariable int id, @RequestBody Bin bin) {

        //make sure we're updating the bin specified by our path...
        if (bin.getClientId() != id) {
            bin.setClientId(id);
        }

        return binDao.updateBin(bin);
    }

    //update a client's address
    @RequestMapping(path = "/clients/{id}/address", method = RequestMethod.PUT)
    public Address updateClientAddressByClientID(@RequestBody Address newAddress, @PathVariable int id) {
        //grab the client info...
        Client client = clientDao.getClientById(id);

        //create a row in our addresses table for the new address...
        Address updatedAddress = addressDao.createAddress(newAddress);
        System.out.println(updatedAddress.getAddressId());

        //set the address_id on our client...
        client.setAddressId(updatedAddress.getAddressId());

        //update client...
        clientDao.updateClient(client);

        return updatedAddress;
    }

    //Method to get the client's bins, and update their bins based on the pick up id

    @PutMapping(path = "/bins/client/{id}")
    public Bin updateBinByPickUpId(@PathVariable int id, @RequestParam int pickupId) {
        // Get the client's bin based on the client ID
        Bin bin = binDao.getBinByClientId(id);
        // Update the bin with the pickup ID

        // Assuming the update is successful, return the updated bin
        return binDao.updateBin(bin);
    }

    @RequestMapping(path = "/total", method = RequestMethod.GET)
    public double getSumOfAllWeight() {
        return binDao.getSumOfAllBins();
    }

    @RequestMapping(path = "/total/clear", method = RequestMethod.GET)
    public int getSumOfAllClearBinsCollected() {
        return binDao.getSumOfAllClearBinsCollected();
    }

    @RequestMapping(path = "/total/green", method = RequestMethod.GET)
    public int getSumOfAllGreensBinsCollected() {
        return binDao.getSumOfAllGreenBinsCollected();
    }

    @RequestMapping(path = "/total/brown", method = RequestMethod.GET)
    public int getSumOfAllBrownBinsCollected() {
        return binDao.getSumOfAllBrownBinsCollected();
    }

    @RequestMapping(path = "/total/all", method = RequestMethod.GET)
    public int getSumOfAllBinsCollected() {
        return binDao.getSumOfAllBinsCollected();
    }

    @DeleteMapping("client/{clientId}")
    public ResponseEntity<String> deleteClient(@PathVariable int clientId) {
        try {
            boolean deleted = clientDao.deleteClientById(clientId);
            if (deleted) {
                return ResponseEntity.ok("Client with ID " + clientId + " deleted successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Client with ID " + clientId + " not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete client with ID " + clientId + ".");
        }
    }
}
