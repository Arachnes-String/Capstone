package com.techelevator.dao;

import com.techelevator.model.Driver;

import java.util.List;

public interface DriverDao {

    //get a driver by id
    Driver getDriverById(int id);

    //get a list of drivers (admin)
    List<Driver> getDrivers();

    //create a drive
    boolean createDriver(Driver driver);

    //update a driver
    Driver updateDriver(Driver driver);

    //delete a drive
    boolean deleteDriver(int driverId);
}
