package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.model.Address;
import com.techelevator.model.Client;
import com.techelevator.model.DriverPickUpDTO;
import com.techelevator.model.PickUp;
import com.techelevator.service.PickUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
public class PickUpController {
    private static final String DEFAULT_PATH = "/pickups";
    private UserDao userDao;
    private DriverDao driverDao;
    private ClientDao clientDao;
    private PickUpDao pickUpDao;
    private AddressDao addressDao;
    private PickUpService pickUpService;

    @Autowired
    public PickUpController(UserDao userDao, DriverDao driverDao, ClientDao clientDao, PickUpDao pickUpDao, AddressDao addressDao, PickUpService pickUpService) {
        this.userDao = userDao;
        this.driverDao = driverDao;
        this.clientDao = clientDao;
        this.pickUpDao = pickUpDao;
        this.addressDao = addressDao;
        this.pickUpService = pickUpService;
    }

    // For admin view
    // See all the completed, pending and completed, potentially 2 values.
    @GetMapping(path = "/pickups")
    public List<PickUp> getAllPickUps() {
        try {
            return pickUpDao.findAll();
        } catch (EmptyResultDataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pickups found", ex);
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving pickups", ex);
        }
    }

    //Route that takes a pickup takes a DriverPickUpDTO and returns an updated DriverDTO
    @GetMapping(path = {DEFAULT_PATH + "/{id}"})
    public PickUp getPickUpById(@PathVariable int id) {
        try {
            return pickUpDao.getPickUpById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pickup found", ex);
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving pickups", ex);
        }
    }

    @GetMapping(path = {DEFAULT_PATH + "/driver/{driverId}"})
    public List<PickUp> getPickUpsByDriverId(@PathVariable int driverId) {
        try {
            return pickUpDao.getPickupsByDriverId(driverId);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pickup found", ex);
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving pickups", ex);
        }
    }

    //get all pick-ups by Client ID for the client
    @GetMapping(path = {DEFAULT_PATH + "/client/{clientId}"})
    public List<PickUp> getPickUpByClientId(@PathVariable int clientId) {
        try {
            return pickUpDao.getPickUpByClientId(clientId);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pickups found", ex);
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving pickups", ex);
        }
    }

    @GetMapping(path = {DEFAULT_PATH + "/driver"})
    public PickUp getPickUpByDriverName(@RequestParam String driverName) {
        return pickUpDao.findByDriverName(driverName);
    }

    //Method for getting the addresses off of a pickup form
    @GetMapping(path = {DEFAULT_PATH + "/address/{pickupId}"})
    public Address getAddressByPickupId(@PathVariable int pickupId) {
        Address currentAddress = new Address();

        try {
            currentAddress = pickUpDao.getAddressByPickupId(pickupId);
            if (currentAddress == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pickup not found");
            }
        } catch (EmptyResultDataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pickups found", ex);
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving pickups", ex);
        }
        return currentAddress;
    }

    //Pulls all the pickups assigned to a driver, as well as any pickups that were unassigned (value of 1)
    @GetMapping(path = {DEFAULT_PATH + "/all/{driverId}"})
    public ResponseEntity<List<DriverPickUpDTO>> getDriverAndUnassignedPickUpsById(@PathVariable int driverId) {
        List<DriverPickUpDTO> allUnassignedAndDriverPickUps = new ArrayList<>();
        List<PickUp> allDriverPickups = pickUpDao.getPickupsByDriverId(driverId);
        List<PickUp> allUnassignedPickups = pickUpDao.getPickupsByDriverId(1);

        try {
            //Add the current driver to the all list
            for (PickUp currentPickUp : allDriverPickups) {
                DriverPickUpDTO currentPickUpDTO = pickUpService.convertToDriverPickupDTO(currentPickUp);
                allUnassignedAndDriverPickUps.add(currentPickUpDTO);
            }
            //Add the unassigned pickups to the all list
            for (PickUp currentPickUp : allUnassignedPickups) {
                DriverPickUpDTO currentPickUpDTO = pickUpService.convertToDriverPickupDTO(currentPickUp);
                allUnassignedAndDriverPickUps.add(currentPickUpDTO);
            }

            return ResponseEntity.ok(allUnassignedAndDriverPickUps);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @PostMapping(path = {DEFAULT_PATH + "/new-pick-up"})
    public boolean createEmptyPickUpForm(@RequestBody PickUp pickUp) {
        //Get client by id first
        int clientId = pickUp.getClientId();
        //Get the client then pull the address id from the client
        Client ourClient = clientDao.getClientById(clientId);
        // Use this client to get the addressid
        int addressId = ourClient.getAddressId();
        //Set our address Id for our pickup
        pickUp.setAddressId(addressId);

        //Set the default value for pickuptime
        pickUp.setDateTimeCompleted(LocalDateTime.MIN);
        //set the default value for driver
        pickUp.setDriverId(0);

        return pickUpDao.createEmptyPickUpForm(pickUp);
    }

    @PutMapping(path = {DEFAULT_PATH + "/update-dto"})
    public ResponseEntity<DriverPickUpDTO> updateDriverPickUpDTO(@RequestBody DriverPickUpDTO driverPickUpDTO) {
        try {
            int pickUpId = driverPickUpDTO.getPickUpId();
            // Retrieve the existing pick-up from the database
            PickUp existingPickUp = pickUpDao.getPickUpById(pickUpId);

            // Update the relevant properties of the existing pick-up using the values from the driverPickUpDTO
            // Perform any additional updates or validations as needed
            PickUp updatedPickUp = pickUpService.convertToPickUp(driverPickUpDTO);

            // Save the updated pick-up back to the database
            pickUpDao.updatePickUpForm(updatedPickUp);
            //Call on pickup method to get the updated value
            updatedPickUp = pickUpDao.getPickUpById(pickUpId);
            String status = updatedPickUp.getStatus();

            //Make any database changes based on the new values
            pickUpService.updatePickUpAndBin(pickUpId, status);

            // Convert the updated pick-up to a DriverPickUpDTO
            DriverPickUpDTO updatedDriverPickUpDTO = pickUpService.convertToDriverPickupDTO(updatedPickUp);

            return ResponseEntity.ok(updatedDriverPickUpDTO);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pick-up found", ex);
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating pick-up", ex);
        }
    }

    //This Method receives a status and pickupId and updates the client bin values
    //Returns a DriverPickUpDTO
    @PutMapping(path = {DEFAULT_PATH + "/update/{id}"})
    public ResponseEntity<DriverPickUpDTO> updatePickUp(@PathVariable int id, @RequestParam(value = "status") String status) {
        // Logic to update the PickUp entry with the given ID using the updatedPickUp object
        try {
            //Update the pickup and pull it from the database
            pickUpService.updatePickUpAndBin(id, status);
            PickUp updatedPickUp = pickUpDao.getPickUpById(id);

            //Now convert the pickup to a driverPickUpDTO
            DriverPickUpDTO driverPickupDTO = pickUpService.convertToDriverPickupDTO(updatedPickUp);

            return ResponseEntity.ok(driverPickupDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //Update the driver on the pickup
    @PutMapping(path = {DEFAULT_PATH + "/pickup/{id}/{driverId}"})
    public ResponseEntity<DriverPickUpDTO> updatePickUpDriver(@PathVariable int id, @PathVariable int driverId) {
        try {
            //Update the pickup with the new driver
            pickUpDao.updatePickUpDriver(id, driverId);
            PickUp updatedPickUp = pickUpDao.getPickUpById(id);

            //Now convert to driverPickUpDTO
            DriverPickUpDTO driverPickUpDTO = pickUpService.convertToDriverPickupDTO(updatedPickUp);

            return ResponseEntity.ok(driverPickUpDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping(path = {DEFAULT_PATH + "/delete/{id}"})
    public boolean deletePickUpForm(@PathVariable int id) {
        return pickUpDao.deletePickUpForm(id);
    }


}
