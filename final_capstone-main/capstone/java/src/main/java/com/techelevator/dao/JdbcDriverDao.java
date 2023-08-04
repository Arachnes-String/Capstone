package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Driver;
import com.techelevator.model.PickUp;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcDriverDao implements DriverDao {

    private JdbcTemplate jdbcTemplate;
    private PickUpDao pickUpDao;

    public JdbcDriverDao(JdbcTemplate jdbcTemplate, PickUpDao pickUpDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.pickUpDao = pickUpDao;
    }

    @Override
    public Driver getDriverById(int id) {
        Driver driver = null;
        String sql = "SELECT driver_id, first_name, last_name, zip, radius FROM drivers WHERE driver_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        try {
            if (results.next()) {
                driver = mapRowToDriver(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (EmptyResultDataAccessException e) {
            throw new DaoException("Driver not found for ID: " + id, e);
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while retrieving driver information", e);
        }
        return driver;
    }

    @Override
    public List<Driver> getDrivers() {
        List<Driver> allDrivers = new ArrayList<>();
        Driver currentDriver = new Driver();
        String sql = "SELECT driver_id, first_name, last_name, zip, radius FROM drivers ";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                currentDriver = mapRowToDriver(results);
                allDrivers.add(currentDriver);
            }
        } catch (
                CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (
                BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (
                EmptyResultDataAccessException e) {
            throw new DaoException("No drivers found", e);
        } catch (
                DataAccessException e) {
            throw new DaoException("Error occurred while retrieving driver information", e);
        }
        return allDrivers;
    }

    @Override
    public boolean createDriver(Driver driver) {
        //create a new driver
        String firstName = driver.getFirstName();
        String lastName = driver.getLastName();
        int userId = driver.getDriverId();
        String zip = driver.getZip();
        double radius = driver.getRadius();

        String sql = "INSERT INTO drivers (driver_id, first_name, last_name, zip, radius) VALUES (?, ?, ?, ?, ?)";

        try {
            int rowsAffected = jdbcTemplate.update(sql, userId, firstName, lastName, zip, radius);
            return rowsAffected > 0; // Return true if at least one row was affected
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while creating the client", e);
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return false;
    }


    @Override
    public Driver updateDriver(Driver driver) {
        int driverId = driver.getDriverId();
        String firstName = driver.getFirstName();
        String lastName = driver.getLastName();
        String zip = driver.getZip();
        double radius = driver.getRadius();

        String sql = "UPDATE drivers SET first_name = ?, last_name = ?, zip = ?, radius = ? WHERE driver_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, firstName, lastName, zip, radius, driverId);
            if (rowsAffected > 0) {
                return driver;
            } else {
                throw new DaoException("Driver not found for ID: " + driverId);
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while updating the driver", e);
        }
    }

    @Override
    public boolean deleteDriver(int driverId) {
        String sql = "DELETE FROM drivers WHERE driver_id = ?";
        // Need to pull into a list all the pickups assigned to this driver
        List<PickUp> driversPickUps = pickUpDao.getPickupsByDriverId(driverId);

        // Need to filter out the pickups that were completed or rejected and only update the pending.
        List<PickUp> pendingPickUps = new ArrayList<>();
        for(PickUp currentPickUp : driversPickUps){
            String currentStatus = currentPickUp.getStatus();
            if(currentStatus.equalsIgnoreCase("pending")){
                pendingPickUps.add(currentPickUp);
            }
        }

        try {
            for (PickUp pickUp : pendingPickUps) {
                int currentPickup = pickUp.getPickUpId();
                int defaultDriver = 1;
                pickUpDao.updatePickUpDriver(currentPickup, defaultDriver);
            }
            jdbcTemplate.update(sql, driverId);
            return true;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (EmptyResultDataAccessException e) {
            throw new DaoException("Driver not found for ID: " + driverId, e);
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while deleting driver information", e);
        } catch (Exception e) {
            // Log the exception stack trace for debugging purposes
            e.printStackTrace();
            throw new DaoException("Unknown error occurred while deleting driver information", e);
        }
    }

    private Driver mapRowToDriver(SqlRowSet results) {
        Driver newDriver = new Driver();

        newDriver.setDriverId(results.getInt("driver_id"));
        newDriver.setFirstName(results.getString("first_name"));
        newDriver.setLastName(results.getString("last_name"));
        newDriver.setZip(results.getString("zip"));
        newDriver.setRadius(results.getDouble("radius"));

        return newDriver;
    }

}
