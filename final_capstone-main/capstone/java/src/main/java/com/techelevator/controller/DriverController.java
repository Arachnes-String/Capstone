package com.techelevator.controller;

import com.techelevator.dao.DriverDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Driver;
import com.techelevator.model.DriverDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class DriverController {
    private static final String DEFAULT_PATH = "/drivers";
    private UserDao userDao;
    private DriverDao driverDao;

    public DriverController(UserDao userDao, DriverDao driverDao) {
        this.userDao = userDao;
        this.driverDao = driverDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = {DEFAULT_PATH}, method = RequestMethod.POST)
    public void registerDriver(@Valid @RequestBody DriverDTO body) {
        //* grab the userID from that userName....
        int userId = userDao.findIdByUsername(body.getUsername());
        body.setUserId(userId);
        // create a new driver object with the user id we grabbed.
        Driver newDriver = new Driver(body);

        //If it is not successful send a bad request. message
        if (!driverDao.createDriver(newDriver)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Driver registration failed.");
        }
    }

    @GetMapping(path= {DEFAULT_PATH+"/all"})
    public ResponseEntity<List<Driver>> getAllDrivers(){
        List<Driver> allDrivers = new ArrayList<>();
        try{
            allDrivers= driverDao.getDrivers();
            return ResponseEntity.ok(allDrivers);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping(path = {DEFAULT_PATH + "/driver/{id}"})
    public Driver getDriver(@PathVariable int id) {
        Driver driver = driverDao.getDriverById(id);
        if (driver == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Driver not found");
        }
        return driver;
    }



    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = {DEFAULT_PATH + "/{id}"}, method = RequestMethod.PUT)
    public void updateDriver(@PathVariable int id, @Valid @RequestBody DriverDTO body) {
        Driver existingDriver = driverDao.getDriverById(id);
        if (existingDriver == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Driver not found");
        }

        // Update the properties of the existing driver with the values from the request body
        existingDriver.setFirstName(body.getFirstName());
        existingDriver.setLastName(body.getLastName());
        existingDriver.setZip(body.getZip());
        existingDriver.setRadius(body.getRadius());

        // Update the driver in the database
        Driver updatedDriver = driverDao.updateDriver(existingDriver);
        if (updatedDriver == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update driver");
        }
    }

    @DeleteMapping({DEFAULT_PATH + "/delete/{driverId}"})
    public ResponseEntity<String> deleteDriver(@PathVariable int driverId) {
        try {
            boolean deleted = driverDao.deleteDriver(driverId);
            if (deleted) {
                return ResponseEntity.ok("Driver with ID " + driverId + " deleted successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Driver with ID " + driverId + " not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete driver with ID " + driverId + ".");
        }
    }


}
