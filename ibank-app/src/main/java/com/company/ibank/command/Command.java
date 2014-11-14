package com.company.ibank.command;

import com.company.ibank.exceptions.CommandException;

import java.util.concurrent.Callable;

public interface Command extends Callable<String>{
    String PERSON_COMMAND = "person";
    String BANK_COMMAND = "bank";
    String VIEW_ALL="view-all";
    String EXIT_COMMAND = "exit";
    String SORT_ACCOUNT = "sort-account";
    String FIND_ACCOUNT = "find-account";
    String FILTER_ACCOUNT = "filter-account";
    String EXCHANGE = "exchange";
    String UNFREEZE = "unfreeze-account";
    String FREEZE = "freeze-account";
    String CREATE_ACCOUNT = "create-account";
    String CLOSE_ACCOUNT = "close-account";

    @Override
    String call() throws CommandException;
}
