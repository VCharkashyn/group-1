package com.company.ibank.command;

import com.company.ibank.exceptions.CommandException;

import java.util.concurrent.Callable;

public interface Command extends Callable<String>{
    String PERSON_COMMAND = "person";
    String BANK_COMMAND = "bank";
    String EXIT_COMMAND = "exit";

    @Override
    String call() throws CommandException;
}
