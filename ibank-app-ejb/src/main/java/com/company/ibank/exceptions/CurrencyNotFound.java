package com.company.ibank.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException
public class CurrencyNotFound extends ServiceException {

    public CurrencyNotFound(String message) {
        super(message);
    }

    public CurrencyNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
