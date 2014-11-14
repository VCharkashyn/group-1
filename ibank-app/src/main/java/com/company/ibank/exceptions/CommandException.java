package com.company.ibank.exceptions;

/**
 * Created by dimon on 28.10.2014.
 */
public class CommandException extends Exception {

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandException(String message) {
        super(message);
    }

}
