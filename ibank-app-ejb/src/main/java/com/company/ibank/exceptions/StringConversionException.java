package com.company.ibank.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException
public class StringConversionException extends Exception {

    public StringConversionException(String message, Throwable cause) {
        super(message, cause);
    }
}
