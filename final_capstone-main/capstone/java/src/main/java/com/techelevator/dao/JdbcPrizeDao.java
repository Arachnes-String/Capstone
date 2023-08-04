package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Prize;
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
public class JdbcPrizeDao implements PrizeDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcPrizeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Prize> getAllPrizes() {
        List<Prize> allPrizes = new ArrayList<>();

        String sql = "SELECT prize_id, name, credit_value FROM prize";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                allPrizes.add(mapRowToPrize(results));
            }
        } catch (
                CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (
                BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (
                EmptyResultDataAccessException e) {
            throw new DaoException("No prizes found", e);
        } catch (
                DataAccessException e) {
            throw new DaoException("Error occurred while retrieving prize information", e);
        }

        return allPrizes;
    }

    @Override
    public Prize getPrizeById(int id) {
        Prize prize = new Prize();

        String sql = "SELECT prize_id, name, credit_value FROM prize WHERE prize_id = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                prize = mapRowToPrize(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (EmptyResultDataAccessException e) {
            throw new DaoException("No prizes found", e);
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while retrieving prize information", e);
        }

        return prize;
    }

    @Override
    public Prize getPrizeByName(String name) {
        Prize prize = new Prize();

        String sql = "SELECT prize_id, name, credit_value FROM prize WHERE name LIKE ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);
            if (results.next()) {
                prize = mapRowToPrize(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (EmptyResultDataAccessException e) {
            throw new DaoException("No prizes found", e);
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while retrieving prize information", e);
        }

        return prize;
    }

    @Override
    public boolean createPrize(Prize prize) {
        String name = prize.getName();
        int creditValue = prize.getCreditValue();

        String sql = "INSERT INTO prize (name, credit_value) VALUES (?, ?)";

        try {
            jdbcTemplate.update(sql, name, creditValue);
            return true;
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while creating a new prize", e);
        }

    }

    @Override
    public boolean updatePrize(Prize prize) {
        String sql = "UPDATE prize SET name = ?, credit_value = ? WHERE prize_id = ?";

        try {
            int rowsUpdated = jdbcTemplate.update(sql, prize.getName(), prize.getCreditValue(), prize.getPrizeId());
            return rowsUpdated > 0;
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while updating the prize", e);
        }
    }

    @Override
    public boolean deletedPrize(int id) {
        String sql = "DELETE FROM prize WHERE prize_id = ?";

        try {
            int rowsDeleted = jdbcTemplate.update(sql, id);
            return rowsDeleted > 0;
        } catch (DataAccessException e) {
            throw new DaoException("Error occurred while deleting the prize", e);
        }
    }

    private Prize mapRowToPrize(SqlRowSet rowSet) {
        Prize prize = new Prize();
        prize.setPrizeId(rowSet.getInt("prize_id"));
        prize.setName(rowSet.getString("name"));
        prize.setCreditValue(rowSet.getInt("credit_value"));

        return prize;

    }
}
