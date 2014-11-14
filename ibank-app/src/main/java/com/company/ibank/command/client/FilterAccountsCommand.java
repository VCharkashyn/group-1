package com.company.ibank.command.client;

import com.company.ibank.command.Command;
import com.company.ibank.exceptions.CommandException;
import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.Account;
import com.company.ibank.model.Currency;
import com.company.ibank.services.BankService;
import com.company.ibank.utils.PrameterHelper;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class FilterAccountsCommand implements Command {

    private BankService bankService;

    public FilterAccountsCommand(BankService bankService) {
        this.bankService = bankService;
    }

    @Override
    public String call() throws CommandException {
        Scanner input = new Scanner(System.in);
        System.out.println("Please write one of parameters to filter (name surname[name], currency[currency])");

        String parameter = input.next();
        String parameterAfter = PrameterHelper.valueOf(parameter.toLowerCase());
        List<Account> filteredAccounts = null;
        try {


            switch (parameterAfter) {

                case PrameterHelper.NAME: {
                    System.out.println("Please write name surname: ");
                    String name = input.next();

                    if (StringUtils.isNotEmpty(name)) {
                        String[] splitName = name.split(" ");
                        if (splitName.length > 1) {
                            filteredAccounts = bankService.filterByName(splitName[0], splitName[1]);
                        }
                    }

                    break;
                }

                case PrameterHelper.CURRENCY: {
                    System.out.println("Please write currency value [BYR, USD, EUR]: ");
                    String currency = input.next();

                    if (StringUtils.isNotEmpty(currency)) {
                        filteredAccounts = bankService.filterByCurrency(Currency.valueOf(currency));
                    }

                    break;
                }

                default: {
                    System.out.println("Unsupported parameter was entered");
                }
            }
        } catch (ServiceException ex) {
            throw new CommandException("ddd", ex);
        }

        for (Account account : filteredAccounts) {
            System.out.println(account);
        }
        return "Accounts filtered by"+ parameterAfter;
    }
}
