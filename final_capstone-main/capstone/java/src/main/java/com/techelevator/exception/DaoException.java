package com.techelevator.exception;

import org.springframework.data.relational.core.sql.SQL;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

import java.sql.BatchUpdateException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class DaoException extends RuntimeException {
    public DaoException() {
        super();
    }
    public DaoException(String message) {
        super(message);
    }
    public DaoException(String message, Exception cause) {
        super(message, cause);
    }

}
