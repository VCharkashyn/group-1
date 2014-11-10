package com.company.ibank.command.client;

import com.company.ibank.command.Command;
import com.company.ibank.exceptions.CommandException;
import com.company.ibank.model.Currency;
import com.company.ibank.model.Money;
import com.company.ibank.services.BankService;
import com.company.ibank.utils.ValidationUtil;
import org.apache.log4j.Logger;

import java.util.Scanner;
import java.util.concurrent.Callable;

public class ExchangeCommand implements Command {
    private static final Logger LOG = Logger.getLogger(ExchangeCommand.class);
    private BankService bankService;

    public ExchangeCommand(BankService bankService) {
        this.bankService = bankService;
    }

    @Override
    public String call() {
        Long id = null;
        Currency currencyNew = null;
        Scanner input = new Scanner(System.in);
        try {
            do {
                System.out.println("Please enter accountID, which you want to close");
                id = ValidationUtil.validateAccountID(input.next());
            } while (id == null);


            do {
                System.out.println("Please enter your currency");
                currencyNew = ValidationUtil.validateCurrency(input.next());
            } while (currencyNew == null);


            bankService.exchange(id, currencyNew);
        } catch (Exception ex) {
            LOG.error("");
        } finally {
            input.close();
        }

        return "Account with ID:"+id+"changed his currency to:"+ currencyNew.getCurrencyName();
    }
}
