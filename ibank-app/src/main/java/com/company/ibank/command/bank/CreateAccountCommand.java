package com.company.ibank.command.bank;

import com.company.ibank.command.Command;
import com.company.ibank.exceptions.CommandException;
import com.company.ibank.model.*;
import com.company.ibank.services.AccountService;
import com.company.ibank.services.PersonService;
import com.company.ibank.utils.ValidationUtil;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class CreateAccountCommand implements Command {
    private static final Logger LOG = Logger.getLogger(CreateAccountCommand.class);
    private PersonService personService;
    private AccountService accountService;

    public CreateAccountCommand(PersonService personService, AccountService accountService) {
        this.personService = personService;
        this.accountService = accountService;
    }

    @Override
    public String call() throws CommandException {
        Scanner reader = new Scanner(System.in);
        Person person = null;
        Long accountId = null;

        try {
            String name;

            System.out.println("Please write client data");
            do {
                System.out.println("Your name : ");
                name = reader.next();
            } while (name == null);

            String surname;
            do {
                System.out.println("Surname : ");
                surname = reader.next();
            } while (surname == null);

            BigDecimal amount;
            do {
                System.out.println("Amount : ");
                amount = ValidationUtil.validateAmount(reader.next());
            } while (amount == null);

            Currency currency;
            do {
                System.out.println("Currency : ");
                currency = ValidationUtil.validateCurrency(reader.next());
            } while (currency == null);

            person = personService.findOrCreatePerson(name, surname);
            accountId = accountService.createAccount(person, amount, currency);
        } catch (Exception ex) {
            throw new CommandException("exception occured", ex);
        } finally {
            reader.close();
        }
        return "Account for person" + person.getName() + " " + person.getSurname() + "accountID: " + accountId;
    }
}
