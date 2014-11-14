package com.company.ibank.command.client;

import com.company.ibank.command.Command;
import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.Account;
import com.company.ibank.services.AccountService;
import com.company.ibank.utils.ValidationUtil;

import java.util.Scanner;
import java.util.concurrent.Callable;

public class FindAccountCommand implements Command {
    private AccountService accountService;

    public FindAccountCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public String call()  {
        Account account = null;
        Scanner input = null;
        try {
            input = new Scanner(System.in);
            System.out.println("Please write account ID : ");

            Long accountId = ValidationUtil.validateAccountID(input.next());
            account = accountService.find(accountId);
        } catch (ServiceException e) {
            e.printStackTrace();
        } finally {
            input.close();
        }

        return account.toString();
    }
}
