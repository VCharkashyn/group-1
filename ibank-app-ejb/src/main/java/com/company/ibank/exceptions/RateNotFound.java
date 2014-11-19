package com.company.ibank.exceptions;


import javax.ejb.ApplicationException;

@ApplicationException
public class RateNotFound extends Exception {

    public RateNotFound(String message) {
        super(message);
    }

    public RateNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
