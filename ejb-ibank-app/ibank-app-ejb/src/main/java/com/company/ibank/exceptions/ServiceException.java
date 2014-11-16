package com.company.ibank.exceptions;


import javax.ejb.ApplicationException;

@ApplicationException
public class ServiceException extends Exception {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
