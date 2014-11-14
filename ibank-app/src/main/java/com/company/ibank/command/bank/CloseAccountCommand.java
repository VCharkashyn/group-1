package com.company.ibank.command.bank;

import com.company.ibank.command.Command;
import com.company.ibank.exceptions.CommandException;
import com.company.ibank.services.AccountService;
import com.company.ibank.services.PersonService;
import com.company.ibank.utils.ValidationUtil;
import org.apache.log4j.Logger;

import java.util.Scanner;
import java.util.concurrent.Callable;


public class CloseAccountCommand implements Command {
    private static final Logger LOG = Logger.getLogger(CloseAccountCommand.class);

    private AccountService accountService;
    private PersonService personService;

    public CloseAccountCommand(AccountService accountService, PersonService personService) {
        this.accountService = accountService;
        this.personService = personService;
    }

    @Override
    public String call() {
        Scanner reader = new Scanner(System.in);
        Long id = null;
        try {
            do {
                System.out.println("Please enter accountID, which you want to close");
                id = ValidationUtil.validateAccountID(reader.next());
            } while (id == null);
            accountService.close(id);
        } catch (Exception ex) {
            LOG.error("");
        } finally {
            reader.close();
        }
        return "Account with identification:" + id + "was closed for person ";
    }
}
