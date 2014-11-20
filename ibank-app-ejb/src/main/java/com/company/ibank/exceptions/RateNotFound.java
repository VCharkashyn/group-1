package com.company.ibank.exceptions;


import javax.ejb.ApplicationException;

@ApplicationException
public class RateNotFound extends ServiceException {

    public RateNotFound(String message) {
        super(message);
    }

    public RateNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
