package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.model.*;
import com.techelevator.service.PickUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class AdminController {

    private static final String DEFAULT_PATH = "/admin";
    private UserDao userDao;
    private DriverDao driverDao;
    private ClientDao clientDao;
    private PickUpDao pickUpDao;
    private AddressDao addressDao;
    private PickUpService pickUpService;
    private PrizeDao prizeDao;

    @Autowired
    public AdminController(UserDao userDao, DriverDao driverDao, ClientDao clientDao, PickUpDao pickUpDao, AddressDao addressDao, PickUpService pickUpService, PrizeDao prizeDao) {
        this.userDao = userDao;
        this.driverDao = driverDao;
        this.clientDao = clientDao;
        this.pickUpDao = pickUpDao;
        this.addressDao = addressDao;
        this.pickUpService = pickUpService;
        this.prizeDao = prizeDao;
    }

    //Route that takes a pickup takes a DriverPickUpDTO and returns a updated DriverDTO
    //update client
    // update driver
    // Delete client //GOAL not necessary
    // Delete driver // some logic update pickups assigned to default.

    @GetMapping(path = {DEFAULT_PATH + "/prizes"})
    public ResponseEntity<List<Prize>> getAllPrizes() {
        List<Prize> allPrizes = new ArrayList<>();
        try {
            allPrizes = prizeDao.getAllPrizes();
            return ResponseEntity.ok(allPrizes);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping(path = {DEFAULT_PATH + "/prizes/{id}"})
    public ResponseEntity<?> getPrizeById(@PathVariable int id) {

        try {
            Prize prize = prizeDao.getPrizeById(id);
            //Check if the name is null to return the correct response
            if (prize.getName() != null) {
                return ResponseEntity.ok(prize);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prize not found");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping(path = {DEFAULT_PATH + "/new-prize"})
    public ResponseEntity<?> createPrize(@RequestBody Prize prize) {
        try {
            boolean created = prizeDao.createPrize(prize);
            if (created) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Prize created successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create prize");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating the prize: " + e.getMessage());
        }
    }

    @PutMapping(path = {DEFAULT_PATH + "/prizes/update/{id}"})
    public ResponseEntity<?> updatePrize(@PathVariable int id, @RequestBody Prize prize) {
        try {
            prize.setPrizeId(id);
            boolean updated = prizeDao.updatePrize(prize);
            if (updated) {
                return ResponseEntity.ok("Prize updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update prize");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping(path = {DEFAULT_PATH + "/prizes/delete/{id}"})
    public ResponseEntity<?> deletePrize(@PathVariable int id) {
        try {
            boolean deleted = prizeDao.deletedPrize(id);
            if (deleted) {
                return ResponseEntity.ok("Prize deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete prize");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    //For Admin view get all pickups as DriverPickUp DTO
    @GetMapping(path = {DEFAULT_PATH +"/pickups-all"})
    public ResponseEntity<List<DriverPickUpDTO>> getAllDriverPickUpDTOs() {
        List<DriverPickUpDTO> allDriverPickUpDTOs = new ArrayList<>();

        try {
            List<PickUp> allPickUps = pickUpDao.findAll();
            //Loop through and add each pickup as the converted object
            for (PickUp currentPickUp : allPickUps) {
                DriverPickUpDTO currentDriverPickUp = pickUpService.convertToDriverPickupDTO(currentPickUp);
                allDriverPickUpDTOs.add(currentDriverPickUp);
            }
            return ResponseEntity.ok(allDriverPickUpDTOs);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
