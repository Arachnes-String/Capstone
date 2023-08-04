package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Address;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcAddressDao implements AddressDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcAddressDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Address getAddressById(int id) {
        Address address = null;
        String sql = "SELECT address_id, client_id, street_address, " +
                "city, state, zip" +
                " FROM addresses WHERE address_id = ?;";

        try{
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
            if (result.next()) {
                address = mapRowToAddress(result);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (EmptyResultDataAccessException e) {
            throw new DaoException("Address not found for ID: " + id, e);
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while retrieving the address", e);
        }
        return address;
    }

    // Get all addressess

    @Override
    public List<Address> getAddresses() {
        return null;
    }

    @Override
    public Address getAddressByClientId(int clientId) {
        Address address = null;
        String sql = "SELECT address_id, client_id, street_address, " +
                "city, state, zip" +
                " FROM addresses WHERE client_id = ?;";

        try{
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, clientId);
            if (result.next()) {
                address = mapRowToAddress(result);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (EmptyResultDataAccessException e) {
            throw new DaoException("Address not found for ID: " + clientId, e);
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while retrieving the address", e);
        }
        return address;
    }

    @Override
    public Address createAddress(Address address) {
        Address createdAddress = null;
        String sql = "INSERT INTO addresses (client_id, street_address, city, state, zip) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING address_id;";
        try {

            int id = jdbcTemplate.queryForObject(sql, int.class, address.getClientId(), address.getStreetAddress(), address.getCity(),
                    address.getState(), address.getZip());

            createdAddress = getAddressById(id);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to the server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error occurred", e);
        } catch (DuplicateKeyException e) {
            throw new DaoException("Duplicate key or unique constraint violation occurred", e);
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while creating the address", e);
        }

        return createdAddress;
    }

    private Address mapRowToAddress(SqlRowSet result){

        Address address = new Address();

        address.setAddressId(result.getInt("address_id"));
        address.setClientId(result.getInt("client_id"));
        address.setStreetAddress(result.getString("street_address"));
        address.setCity(result.getString("city"));
        address.setState(result.getString("state"));
        address.setZip(result.getString("zip"));


        return address;
    }

}
