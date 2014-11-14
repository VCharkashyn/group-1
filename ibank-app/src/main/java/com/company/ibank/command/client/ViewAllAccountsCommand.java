package com.company.ibank.command.client;


import com.company.ibank.command.Command;
import com.company.ibank.exceptions.CommandException;
import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.Account;
import com.company.ibank.services.AccountService;

import java.util.concurrent.Callable;

public class ViewAllAccountsCommand implements Command {
    private AccountService accountService;

    public ViewAllAccountsCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public String call() throws CommandException {
        System.out.println("All accounts:");

        try {
            for (Account account : accountService.getAllAccounts()) {
                System.out.println(account);
            }
        } catch (ServiceException e) {
            throw new CommandException("", e);
        }
        return "All accounts was printed";
    }
}
