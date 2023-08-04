package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Client;
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
public class JdbcClientDao implements ClientDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcClientDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Client getClientById(int id) {
        Client client = null;

        String sql = "SELECT * FROM clients WHERE client_id = ?;";

        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
            if (result.next()) {
                client = mapRowToClient(result);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (EmptyResultDataAccessException e) {
            throw new DaoException("Client not found for ID: " + id, e);
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while retrieving client information", e);
        }

        return client;
    }

    @Override
    public List<Client> getClients() {
        List<Client> clients = new ArrayList<>();

        String sql = "SELECT client_id, first_name, last_name, address_id, phone_number, email_address" +
                " FROM clients;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Client client = mapRowToClient(results);
                clients.add(client);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (EmptyResultDataAccessException e) {
            throw new DaoException("No clients found", e);
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while retrieving clients", e);
        }

        return clients;
    }

    @Override
    public boolean createClient(Client client) {
        boolean success = false;

        String sql = "INSERT INTO clients (client_id, first_name, last_name, address_id, " +
                "phone_number, email_address) VALUES (?, ?, ?, ?, ?, ?);";

        try {
            jdbcTemplate.update(sql, client.getClientId(), client.getFirstName(), client.getLastName(),
                    client.getAddressId(), client.getPhoneNumber(), client.getEmailAddress());
            success = true;
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while creating the driver", e);
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return success;
    }

    @Override
    public Client updateClient(Client client) {
        Client updatedClient = null;

        String sql = "UPDATE clients SET " +
                "first_name = ?, " +
                "last_name = ?, " +
                "address_id = ?, " +
                "phone_number = ?, " +
                "email_address = ? " +
                "WHERE client_id = ?;";

        try {
            jdbcTemplate.update(sql, client.getFirstName(), client.getLastName(), client.getAddressId(),
                    client.getPhoneNumber(), client.getEmailAddress(), client.getClientId());
            updatedClient = getClientById(client.getClientId());

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (EmptyResultDataAccessException e) {
            throw new DaoException("Client not found for ID: " + client.getClientId(), e);
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while retrieving client information", e);
        }


        return updatedClient;
    }

    @Override
    public boolean deleteClient(Client client) {
        boolean success = false;
        String sqlDeleteBin = "DELETE FROM bins WHERE client_id = ?;";
        String sqlDeleteClient = "DELETE FROM clients WHERE client_id = ?;";

        try {

            jdbcTemplate.update(sqlDeleteBin, client.getClientId());
            jdbcTemplate.update(sqlDeleteClient, client.getClientId());
            success = true;

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (EmptyResultDataAccessException e) {
            throw new DaoException("Client not found for ID: " + client.getClientId(), e);
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while retrieving client information", e);
        }

        return success;
    }

    @Override
    public boolean deleteClientById(int id) {
        boolean success = false;
        String sqlDeletePickups = "DELETE FROM pick_ups WHERE client_id = ?;";
        String sqlDeletePrizeClient = "DELETE FROM prize_client WHERE client_id = ?;";
        String sqlDeleteBins = "DELETE FROM bins WHERE client_id = ?;";
        String sqlDeleteClient = "DELETE FROM clients WHERE client_id = ?;";

        try {
            jdbcTemplate.update(sqlDeletePickups, id);
            jdbcTemplate.update(sqlDeletePrizeClient, id);
            jdbcTemplate.update(sqlDeleteBins, id);
            jdbcTemplate.update(sqlDeleteClient, id);
            success = true;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (EmptyResultDataAccessException e) {
            throw new DaoException("Client not found for ID: " + id, e);
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while deleting client information", e);
        }
        return success;
    }

    private Client mapRowToClient(SqlRowSet result) {
        Client client = new Client();
        client.setClientId(result.getInt("client_id"));
        client.setFirstName(result.getString("first_name"));
        client.setLastName(result.getString("last_name"));
        client.setAddressId(result.getInt("address_id"));
        client.setPhoneNumber(result.getString("phone_number"));
        client.setEmailAddress(result.getString("email_address"));

        return client;

    }

}
