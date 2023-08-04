package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Bin;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


@Component
public class JdbcBinDao implements BinDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcBinDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Bin getBinByClientId(int clientId) {
        Bin bin = null;
        String sql = "SELECT * FROM bins WHERE client_id = ?;";

        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, clientId);
            if(result.next()){
                bin = mapRowToBin(result);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (EmptyResultDataAccessException e) {
            throw new DaoException("Bin not found for ID: " + clientId, e);
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while retrieving client information", e);
        }

        return bin;
    }

    @Override
    public Double getSumOfAllBins() {
        String sql = "SELECT SUM(total_weight) AS combined_weight FROM bins;";

        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
            if (result.next()){
               return result.getDouble("combined_weight");
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (EmptyResultDataAccessException e) {
            throw new DaoException("no bins");
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while retrieving client information", e);
        }

        return null;
    }

    @Override
    public int getSumOfAllClearBinsCollected() {
        String sql = "SELECT SUM(clear_bins) AS total FROM bins;";

        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
            if (result.next()){
                return result.getInt("total");
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (EmptyResultDataAccessException e) {
            throw new DaoException("no bins");
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while retrieving client information", e);
        }

        return 0;
    }

    @Override
    public int getSumOfAllBrownBinsCollected() {
        String sql = "SELECT SUM(brown_bins) AS total FROM bins;";

        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
            if (result.next()){
                return result.getInt("total");
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (EmptyResultDataAccessException e) {
            throw new DaoException("no bins");
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while retrieving client information", e);
        }

        return 0;
    }

    @Override
    public int getSumOfAllGreenBinsCollected() {
        String sql = "SELECT SUM(green_bins) AS total FROM bins;";

        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
            if (result.next()){
                return result.getInt("total");
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (EmptyResultDataAccessException e) {
            throw new DaoException("no bins");
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while retrieving client information", e);
        }

        return 0;
    }

    @Override
    public int getSumOfAllBinsCollected() {
        String sql = "SELECT SUM(green_bins + brown_bins + clear_bins) AS total FROM bins;";

        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
            if (result.next()){
                return result.getInt("total");
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (EmptyResultDataAccessException e) {
            throw new DaoException("no bins");
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while retrieving client information", e);
        }


        return 0;
    }

    @Override
    public boolean createBinForClientByClientID(int clientId) {
        boolean success = false;
        String sql = "INSERT INTO bins (client_id, clear_bins, green_bins, brown_bins, total_weight, credits, rejections) " +
                "VALUES (?, 0, 0, 0, 0.0, 0, 0);";

        try {
           jdbcTemplate.update(sql, clientId);
           success = true;

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (EmptyResultDataAccessException e) {
            throw new DaoException("Bin not found for client_id: " + clientId, e);
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while retrieving client information", e);
        }


        return success;
    }

    @Override
    public Bin updateBin(Bin bin) {
        Bin updatedBin = null;
        String sql = "UPDATE bins " +
                "SET clear_bins = ?, " +
                "brown_bins = ?, " +
                "green_bins = ?, " +
                "total_weight = ?, " +
                "credits = ?, " +
                "rejections = ? " +
                "WHERE client_id = ?;";
        try {
            jdbcTemplate.update(sql, bin.getClearBins(), bin.getBrownBins(), bin.getGreenBins(),
                    bin.getTotalWeight(), bin.getCredits(), bin.getRejections(), bin.getClientId());

            updatedBin = getBinByClientId(bin.getClientId());

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (EmptyResultDataAccessException e) {
            throw new DaoException("Bin not found for client_id: " + bin.getClientId(), e);
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while retrieving client information", e);
        }

        return updatedBin;
    }

    @Override
    public boolean deleteBinByClientId(int clientId) {
        boolean success = false;
        String sql = "DELETE FROM bins WHERE client_id = ?";

        try {
          jdbcTemplate.update(sql, clientId);
          success = true;

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (EmptyResultDataAccessException e) {
            throw new DaoException("Bin not found for ID: " + clientId, e);
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while retrieving client information", e);
        }


        return success;
    }





    private Bin mapRowToBin(SqlRowSet result){
        Bin bin = new Bin();

        bin.setClientId(result.getInt("client_id"));
        bin.setClearBins(result.getInt("clear_bins"));
        bin.setBrownBins(result.getInt("brown_bins"));
        bin.setGreenBins(result.getInt("green_bins"));
        bin.setTotalWeight(result.getDouble("total_weight"));
        bin.setCredits(result.getInt("credits"));
        bin.setRejections(result.getInt("rejections"));

        return bin;
    }
}
