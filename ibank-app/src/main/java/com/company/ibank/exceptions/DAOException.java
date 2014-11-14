package com.company.ibank.exceptions;

/**
 * Created by dimon on 08.11.2014.
 */
public class DAOException extends Exception {

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
