package com.techelevator.service;

import com.techelevator.dao.AddressDao;
import com.techelevator.dao.BinDao;
import com.techelevator.dao.PickUpDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class PickUpService {
    private final PickUpDao pickUpDao;
    private final BinDao binDao;
    private final AddressDao addressDao;
    private final UserDao userDao;

    @Autowired
    public PickUpService(PickUpDao pickUpDao, BinDao binDao, AddressDao addressDao, UserDao userDao) {
        this.pickUpDao = pickUpDao;
        this.binDao = binDao;
        this.addressDao = addressDao;
        this.userDao = userDao;
    }

    //Pull the data object and set it as the status.
    //Build another method to make a DriverPickUpDTO
    @Transactional
    public void updatePickUpAndBin(int pickUpId, String status) {

        //Set the updated pickup status to the status send
        PickUp databasePickUp = pickUpDao.getPickUpById(pickUpId);
        if (databasePickUp == null) {
            throw new IllegalArgumentException("Invalid pick-up ID");
        }

        LocalDateTime timeCompleted = LocalDateTime.now();

        // Update the associated Bin based on the status
        if (status.equalsIgnoreCase("Completed")) {
            //Update the pickUp form with new values
            databasePickUp.setStatus(capitalizeFirstLetter(status));
            databasePickUp.setDateTimeCompleted(timeCompleted);

            // Update the associated Bin based on the status
            Bin bin = binDao.getBinByClientId(databasePickUp.getClientId());
            if (bin != null) {
                // Logic for updating the bin when the status is Completed
                //Update the bin counts
                incrementCountIfTrue(databasePickUp, bin);
                //Add the weight to the client's bin
                incrementBinWeight(databasePickUp, bin);
                //Update the credits after updating the bin
                calculateCredits(bin);
                //Update the bin in the database
                binDao.updateBin(bin);
            }
        } else if (status.equalsIgnoreCase("Rejected")) {
            // Logic for handling the rejection of the pick-up
            //Update the pickUp form with new values
            databasePickUp.setStatus(capitalizeFirstLetter(status));
            databasePickUp.setDateTimeCompleted(timeCompleted);

            // Only update the client's rejections in their bins, and everything else stays the same
            Bin bin = binDao.getBinByClientId(databasePickUp.getClientId());
            if (bin != null) {
                // Logic for updating the bin when the status is Rejected
                incrementRejections(bin);
                binDao.updateBin(bin);
            }
        } else if (status.equalsIgnoreCase("Pending")) {
            // Update the pickUp status to "Pending"
            databasePickUp.setStatus("Pending");
            databasePickUp.setDateTimeCompleted(LocalDateTime.parse("9999-12-31 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            // Exit the method since no further processing is needed for "Pending" status
        } else {
            // Handle invalid status value
            throw new IllegalArgumentException("Invalid status value");
        }
        // Logic to update the PickUp entry with the given ID using the updatedPickUp object
        pickUpDao.updatePickUpForm(databasePickUp);
    }


    // Conversion logic here to create a DriverPickupDTO from a PickUp object
    // Return the created DriverPickupDTO
    public DriverPickUpDTO convertToDriverPickupDTO(PickUp pickUp) {

        DriverPickUpDTO driverPickUp = new DriverPickUpDTO();
        Address clientAddress = new Address();

        //Get the data from the current pickUp
        //PickupId
        driverPickUp.setPickUpId(pickUp.getPickUpId());
        //DriverId
        driverPickUp.setDriverId(pickUp.getDriverId());
        //ClientId
        driverPickUp.setClientId(pickUp.getClientId());
        //date time Requested
        driverPickUp.setDateTimeRequested(pickUp.getDateTimeRequested());
        //dateTime completed
        driverPickUp.setDateTimeCompleted(pickUp.getDateTimeCompleted());
        //bins
        driverPickUp.setBrownBinFull(pickUp.isBrownBinFull());
        driverPickUp.setGreenBinFull(pickUp.isGreenBinFull());
        driverPickUp.setClearBinFull(pickUp.isClearBinFull());
        //status
        driverPickUp.setStatus(pickUp.getStatus());
        //weight
        driverPickUp.setWeight(pickUp.getWeight());


        //Need to use address ID to get address
        int addressId = pickUp.getAddressId();

        clientAddress = addressDao.getAddressById(addressId);

        driverPickUp.setStreetAddress(clientAddress.getStreetAddress());
        driverPickUp.setCity(clientAddress.getCity());
        driverPickUp.setState(clientAddress.getState());
        driverPickUp.setZip(clientAddress.getZip());

        return driverPickUp;

    }

    public PickUp convertToPickUp(DriverPickUpDTO driverPickUpDTO) {
        PickUp pickUp = new PickUp();

        //Method to get the address ID
        int clientId = driverPickUpDTO.getClientId();
        Address clientAddress = addressDao.getAddressByClientId(clientId);
        int addressId = clientAddress.getAddressId();

        // Set the data from the DTO to the PickUp object
        pickUp.setPickUpId(driverPickUpDTO.getPickUpId());
        pickUp.setDriverId(driverPickUpDTO.getDriverId());
        pickUp.setClientId(driverPickUpDTO.getClientId());
        pickUp.setAddressId(addressId);
        pickUp.setDateTimeRequested(driverPickUpDTO.getDateTimeRequested());
        pickUp.setDateTimeCompleted(driverPickUpDTO.getDateTimeCompleted());
        pickUp.setBrownBinFull(driverPickUpDTO.isBrownBinFull());
        pickUp.setGreenBinFull(driverPickUpDTO.isGreenBinFull());
        pickUp.setClearBinFull(driverPickUpDTO.isClearBinFull());
        pickUp.setStatus(driverPickUpDTO.getStatus());
        pickUp.setWeight(driverPickUpDTO.getWeight());

        return pickUp;
    }

    //Conversion Logic to convert a client to a client DTO
    public ClientDTO convertToClientDTO(Client client) {
        ClientDTO newClientDTO = new ClientDTO();
        Address clientAddress = new Address();

        //Get the username
        User user = userDao.getUserById(client.getClientId());
        newClientDTO.setUsername(user.getUsername());
        //get the data from client
        newClientDTO.setUserId(client.getClientId());
        newClientDTO.setFirstName(client.getFirstName());
        newClientDTO.setLastName(client.getLastName());
        newClientDTO.setAddressId(client.getAddressId());
        newClientDTO.setPhoneNumber(client.getPhoneNumber());
        newClientDTO.setEmailAddress(client.getEmailAddress());

        int addressId = client.getAddressId();
        // get the data from the address
        clientAddress = addressDao.getAddressById(addressId);

        newClientDTO.setStreetAddress(clientAddress.getStreetAddress());
        newClientDTO.setCity(clientAddress.getCity());
        newClientDTO.setState(clientAddress.getState());
        newClientDTO.setZip(clientAddress.getZip());

        return newClientDTO;

    }


    private void incrementBinWeight(PickUp databasePickUp, Bin bin) {
        double pickUpWeight = databasePickUp.getWeight();
        double currentWeight = bin.getTotalWeight();
        double newWeight = 0.0;

        //Add the two value together
        newWeight = pickUpWeight + currentWeight;

        //Set the new value
        bin.setTotalWeight(newWeight);

    }

    private void incrementCountIfTrue(PickUp databasePickUp, Bin bin) {
        //Get the bins from the database
        int brownBins = bin.getBrownBins();
        int clearBins = bin.getClearBins();
        int greenBins = bin.getGreenBins();

        //Update the bins if the value is true.
        if (databasePickUp.isBrownBinFull()) {
            brownBins++;
        }
        if (databasePickUp.isClearBinFull()) {
            clearBins++;
        }
        if (databasePickUp.isGreenBinFull()) {
            greenBins++;
        }

        //Set the new bin value
        bin.setBrownBins(brownBins);
        bin.setClearBins(clearBins);
        bin.setGreenBins(greenBins);

    }


    private void incrementRejections(Bin bin) {
        int currentRejections = bin.getRejections();
        currentRejections++;

        //Set the new value
        bin.setRejections(currentRejections);

    }


    private void calculateCredits(Bin bin) {
        double totalWeight = bin.getTotalWeight();
        // Round the totalWeight to the nearest whole number
        int roundedWeight = (int) Math.round(totalWeight);

        // Set the credits value to the roundedWeight
        bin.setCredits(roundedWeight);
    }

    private String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
}