package com.company.ibank.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException
public class UnsupportedChangeCurrrencyRequest extends ServiceException {
    public UnsupportedChangeCurrrencyRequest(String message) {
        super(message);
    }
}
