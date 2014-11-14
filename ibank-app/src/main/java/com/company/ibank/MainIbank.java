package com.company.ibank;

import com.company.ibank.command.Command;
import com.company.ibank.exceptions.CommandException;
import com.company.ibank.utils.ActionHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;


public class MainIbank {
    private static final Logger log = Logger.getLogger(MainIbank.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Choose please corresponding role and enter word in brackets: " +
                    "[bank] - Bank, [person] - Person, [exit] - Exit");

            boolean isNotExit = true;
            String command;
            do {
                System.out.println("Please write any command: ");
                command = input.next();

                if (StringUtils.isNotEmpty(command)) {
                    if (StringUtils.equalsIgnoreCase(Command.EXIT_COMMAND, command)) {
                        isNotExit = false;
                    } else if (StringUtils.equalsIgnoreCase("bank", command)) {
                        boolean isNotExitBank = true;

                        do {
                            System.out.println("Choose any command:");
                            System.out.println("create - create account");
                            System.out.println("close - close existing account");
                            System.out.println("freeze - freeze account by id");
                            System.out.println("unfreeze - unfreeze account by id");
                            System.out.println("exit - go back");

                            String commandBank = input.next();
                            if (Command.EXIT_COMMAND.equals(commandBank)) {
                                isNotExitBank = false;
                            } else {
                                System.out.println(ActionHelper.getInstance().getCommandAndExecute(commandBank).get());
                            }
                        } while (isNotExitBank);

                    } else if (StringUtils.equalsIgnoreCase("client", command)) {
                        boolean isNotExitClient = true;

                        do {
                            System.out.println("exchange - exchange your account");
                            System.out.println("transfer - transfer from 1 account to another");
                            System.out.println("sort - sort your account");
                            System.out.println("search - search account by id");
                            System.out.println("filter - filter accounts by currency");
                            System.out.println("exit - go back");

                            String commandBank = input.next();
                            if (Command.EXIT_COMMAND.equals(commandBank)) {
                                isNotExitClient = false;
                            } else {
                                System.out.println(ActionHelper.getInstance().getCommandAndExecute(commandBank).get());
                            }

                        } while (isNotExitClient);
                    }
                }
            } while (isNotExit);
        }
        catch (CommandException e) {
            log.error("Sorry, Exception occurred", e);
        }
        finally {
            input.close();
            ActionHelper.getInstance().close();
        }
    }
}
