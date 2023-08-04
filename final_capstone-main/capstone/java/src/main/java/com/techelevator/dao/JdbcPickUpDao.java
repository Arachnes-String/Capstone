package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Address;
import com.techelevator.model.PickUp;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPickUpDao implements PickUpDao {

    private JdbcTemplate jdbcTemplate;
    private AddressDao addressDao;

    public JdbcPickUpDao(JdbcTemplate jdbcTemplate, AddressDao addressDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.addressDao = addressDao;
    }

    @Override
    public List<PickUp> findAll() {
        List<PickUp> allPickUps = new ArrayList<>();
        String sql = "SELECT pick_up_id, date_time_created, date_time_requested, client_id, address_id, driver_id, date_time_completed," +
                " clear_bin_full, green_bin_full, brown_bin_full, weight, status " +
                "FROM pick_ups;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                allPickUps.add(mapRowToPickUp(results));
            }

        } catch (
                CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (
                BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (
                EmptyResultDataAccessException e) {
            throw new DaoException("No pickups found", e);
        } catch (
                DataAccessException e) {
            throw new DaoException("Error occurred while retrieving pick up information", e);
        }

        return allPickUps;
    }


    @Override
    public PickUp getPickUpById(int pickUpId) {
        PickUp pickUp = new PickUp();
        String sql = "SELECT pick_up_id, date_time_created, date_time_requested, date_time_completed, address_id, " +
                " client_id, driver_id, clear_bin_full, green_bin_full, brown_bin_full, weight, status FROM pick_ups " +
                "WHERE pick_up_id = ?";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, pickUpId);
            if (rowSet.next()) {
                pickUp = mapRowToPickUp(rowSet);
            }
        } catch (
                CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (
                BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (
                EmptyResultDataAccessException e) {
            throw new DaoException("Pick Up not found for ID: " + pickUpId, e);
        } catch (
                DataAccessException e) {
            throw new DaoException("Error occurred while retrieving pick up information", e);
        }
        return pickUp;
    }

    @Override
    public List<PickUp> getPickUpByClientId(int clientId) {
        List<PickUp> allPickUps = new ArrayList<>();
        String sql = "SELECT pick_up_id, date_time_created, date_time_requested, date_time_completed, address_id, " +
                "client_id, driver_id, clear_bin_full, green_bin_full, brown_bin_full, weight, status FROM pick_ups " +
                "WHERE client_id = ?";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, clientId);
            while (rowSet.next()) {
                allPickUps.add(mapRowToPickUp(rowSet));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (EmptyResultDataAccessException e) {
            throw new DaoException("No Pick Up found for Client ID: " + clientId, e);
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while retrieving pick up information", e);
        }
        return allPickUps;
    }


    @Override
    public List<PickUp> getPickupsByDriverId(int driverId) {
        List<PickUp> pickUp = new ArrayList<>();
        String sql = "SELECT pick_up_id, date_time_created, date_time_requested, date_time_completed, address_id, " +
                " client_id, driver_id, clear_bin_full, green_bin_full, brown_bin_full, weight, status FROM pick_ups " +
                "WHERE driver_id = ?";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, driverId);
            while (rowSet.next()) {
                pickUp.add(mapRowToPickUp(rowSet));
            }
        } catch (
                CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (
                BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (
                EmptyResultDataAccessException e) {
            throw new DaoException("Picks Up not found for ID: " + driverId, e);
        } catch (
                DataAccessException e) {
            throw new DaoException("Error occurred while retrieving pick up information", e);
        }
        return pickUp;
    }

    @Override
    public Address getAddressByPickupId(int pickUpId) {

        PickUp currentPickup = getPickUpById(pickUpId);
        int clientId = currentPickup.getClientId();
        Address currentAddress = new Address();

        try {
            currentAddress = addressDao.getAddressByClientId(clientId);
        } catch (
                CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (
                BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (
                EmptyResultDataAccessException e) {
            throw new DaoException("Address not found for ID: " + clientId, e);
        } catch (
                DataAccessException e) {
            throw new DaoException("Error occurred while retrieving pick up information", e);
        }
        return currentAddress;
    }

    //Need to fix the errors here and change it to name.

    @Override
    public PickUp findByDriverName(String driverName) {
        PickUp pickUp = new PickUp();
        String sql = "SELECT pick_up_id, date_time_created, date_time_requested, date_time_completed, address_id, " +
                " client_id, driver_id, clear_bin_full, green_bin_full, brown_bin_full, weight FROM pick_ups " +
                "WHERE driver_name = ?";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, driverName);
            if (rowSet.next()) {
                pickUp = mapRowToPickUp(rowSet);
            }
        } catch (
                CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (
                BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (
                EmptyResultDataAccessException e) {
            throw new DaoException("Pick Up not found for name: " + driverName, e);
        } catch (
                DataAccessException e) {
            throw new DaoException("Error occurred while retrieving pick up information", e);
        }
        return pickUp;
    }

    @Override
    public boolean updatePickUpForm(PickUp pickUp) {
        int pickUpId = pickUp.getPickUpId();
        int clientId = pickUp.getClientId();
        int addressId = pickUp.getAddressId();
        int driverId = pickUp.getDriverId();
        LocalDateTime timeRequested = pickUp.getDateTimeRequested();
        LocalDateTime timeCompleted = pickUp.getDateTimeCompleted();
        double weight = pickUp.getWeight();
        boolean clearBin = pickUp.isClearBinFull();
        boolean greenBin = pickUp.isGreenBinFull();
        boolean brownBin = pickUp.isBrownBinFull();
        String status = pickUp.getStatus();

        String sql = "UPDATE pick_ups SET client_id = ?, address_id = ?, driver_id = ?, " +
                "date_time_requested = ?, date_time_completed = ?, weight = ?, clear_bin_full = ?, " +
                "brown_bin_full = ?, green_bin_full = ?, status = ? WHERE pick_up_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(
                    sql,
                    clientId,
                    addressId,
                    driverId,
                    timeRequested,
                    timeCompleted,
                    weight,
                    clearBin,
                    brownBin,
                    greenBin,
                    status,
                    pickUpId);

            return rowsAffected > 0;
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while updating the pick-up form", e);
        }
    }

    @Override
    public boolean updatePickUpDriver(int pickUpId, int driverId) {
        String sql = "UPDATE pick_ups SET driver_id = ? WHERE pick_up_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, driverId, pickUpId);
            return rowsAffected > 0;
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while updating the pickup's driverId", e);
        }
    }


   // Create a new pickUP
    @Override
    public boolean createEmptyPickUpForm(PickUp pickUp) {
        int clientId = pickUp.getClientId();
        int addressId = pickUp.getAddressId();

        //Default value for driver
        int driverId = 1;

        //Log the time the request is created
        LocalDateTime timeCreated = LocalDateTime.now();

        LocalDateTime timeRequested = pickUp.getDateTimeRequested();

        //Default value for time completed is the MIN
        LocalDateTime timeCompleted = LocalDateTime.MIN;

        double weight = pickUp.getWeight();
        boolean clearBin = pickUp.isClearBinFull();
        boolean greenBin = pickUp.isGreenBinFull();
        boolean brownBin = pickUp.isBrownBinFull();

        //is created here
        String status = "Pending";

        String sql = "INSERT INTO pick_ups (client_id, address_id, driver_id, date_time_created, date_time_requested, date_time_completed, weight, clear_bin_full," +
                " brown_bin_full, green_bin_full, status ) VALUES (?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?)";
        try {
            jdbcTemplate.update(
                    sql,
                    clientId,
                    addressId,
                    driverId,
                    timeCreated,
                    timeRequested,
                    timeCompleted,
                    weight,
                    clearBin,
                    greenBin,
                    brownBin,
                    status);

            return true;
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while creating an empty pick-up form", e);
        }
    }

    @Override
    public boolean deletePickUpForm(int pickUpId) {
        String sql = "DELETE FROM pick_ups WHERE pick_up_id = ?";
        try {
            jdbcTemplate.update(sql, pickUpId);
            return true;
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while deleting the pick-up form", e);
        }
    }


    private PickUp mapRowToPickUp(SqlRowSet rowSet) {
        PickUp newPickUp = new PickUp();
        newPickUp.setPickUpId(rowSet.getInt("pick_up_id"));
        newPickUp.setDateTimeCreated(rowSet.getTimestamp("date_time_created").toLocalDateTime());
        newPickUp.setDateTimeRequested(rowSet.getTimestamp("date_time_requested").toLocalDateTime());
        newPickUp.setDateTimeCompleted(rowSet.getTimestamp("date_time_completed").toLocalDateTime());
        newPickUp.setAddressId(rowSet.getInt("address_id"));
        newPickUp.setClientId(rowSet.getInt("client_id"));
        newPickUp.setDriverId(rowSet.getInt("driver_id"));
        newPickUp.setStatus(rowSet.getString("status"));
        newPickUp.setClearBinFull(rowSet.getBoolean("clear_bin_full"));
        newPickUp.setGreenBinFull(rowSet.getBoolean("green_bin_full"));
        newPickUp.setBrownBinFull(rowSet.getBoolean("brown_bin_full"));
        newPickUp.setWeight(rowSet.getDouble("weight"));
        return newPickUp;
    }

}
