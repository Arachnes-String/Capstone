package com.techelevator.dao;

import com.techelevator.model.Address;
import com.techelevator.model.PickUp;

import java.util.List;

public interface PickUpDao {
    List<PickUp> findAll();

    PickUp getPickUpById(int pickUpId);

    List<PickUp> getPickUpByClientId(int clientId);

    List<PickUp> getPickupsByDriverId(int driverId);

    Address getAddressByPickupId(int pickUpId);

    PickUp findByDriverName(String driverName);

    boolean updatePickUpForm(PickUp pickUp);

    boolean updatePickUpDriver(int pickUpId, int driverId);

    boolean createEmptyPickUpForm(PickUp pickUp);

    boolean deletePickUpForm(int pickUpId);
}
