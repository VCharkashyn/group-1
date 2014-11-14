package com.company.ibank.command.client;

import com.company.ibank.command.Command;
import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.Account;
import com.company.ibank.services.BankService;
import com.company.ibank.utils.PrameterHelper;
import java.util.List;
import java.util.Scanner;


public class SortAccountsCommand implements Command {
    private BankService bankService;

    public SortAccountsCommand(BankService bankService) {
        this.bankService = bankService;
    }

    @Override
    public String call() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please write one of parameters to filter (name surname[name], currency[currency])");

        String parameter = input.next();
        String parameterAfter = PrameterHelper.valueOf(parameter.toLowerCase());
        List<Account> sorteredAccounts = null;
        try {
            switch (parameterAfter) {
                case PrameterHelper.NAME: {
                    sorteredAccounts = bankService.sortByName();
                    break;
                }
                case PrameterHelper.CURRENCY: {
                    sorteredAccounts = bankService.sortByCurrency();
                    break;
                }
                default: {
                    System.out.println("Unsupported parameter was entered");
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        for (Account account : sorteredAccounts) {
            System.out.println(account);
        }
        return "Accounts sorted by" + parameterAfter;
    }
}
