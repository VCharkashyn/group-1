package com.company.ibank.utils;

import com.company.ibank.command.Command;
import com.company.ibank.command.NoCommand;
import com.company.ibank.command.bank.CloseAccountCommand;
import com.company.ibank.command.bank.CreateAccountCommand;
import com.company.ibank.command.bank.FreezeAccountCommand;
import com.company.ibank.command.bank.UnfreezeAccountCommand;
import com.company.ibank.command.client.*;
import com.company.ibank.dao.AccountDAO;
import com.company.ibank.dao.AccountDAOImpl;
import com.company.ibank.dao.PersonDAO;
import com.company.ibank.dao.PersonDAOImpl;
import com.company.ibank.exceptions.CommandException;
import com.company.ibank.model.Person;
import com.company.ibank.services.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class ActionHelper {

    private static Map<String, Command> commands;

    private static ActionHelper instance = new ActionHelper();
    private PersonService personService;
    private AccountService accountService;
    private BankService bankService;
    private final ExecutorService pool;

    private final PersonDAO personDAO = new PersonDAOImpl();
    private final AccountDAO accountDAO = new AccountDAOImpl(personDAO);

    private ActionHelper() {
        personService = new PersonServiceImpl(personDAO);
        accountService = new AccountServiceImpl(accountDAO);
        bankService = new BankServiceImpl(accountDAO);

        commands = new HashMap<String, Command>() {{
            put(Command.CLOSE_ACCOUNT, new CloseAccountCommand(accountService, personService));
            put(Command.CREATE_ACCOUNT, new CreateAccountCommand(personService, accountService));
            put(Command.FREEZE, new FreezeAccountCommand(accountService));
            put(Command.UNFREEZE, new UnfreezeAccountCommand(accountService));
            put(Command.EXCHANGE, new ExchangeCommand(bankService));
            put(Command.FILTER_ACCOUNT, new FilterAccountsCommand(bankService));
            put(Command.FIND_ACCOUNT, new FindAccountCommand(accountService));
            put(Command.SORT_ACCOUNT, new SortAccountsCommand(bankService));
            put(Command.VIEW_ALL, new ViewAllAccountsCommand(accountService));
        }};
        pool = Executors.newFixedThreadPool(10);
    }

    public Future<String> getCommandAndExecute(final String action) throws CommandException {
        Command command = commands.get(action);
        if (command == null) {
            command = new NoCommand();
        }

        return pool.submit(command);
    }

    public static ActionHelper getInstance() {
        return instance;
    }

    public void close() {
        pool.shutdown();
    }
}

