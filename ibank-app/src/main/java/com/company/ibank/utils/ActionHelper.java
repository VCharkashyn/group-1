package com.company.ibank.utils;

import com.company.ibank.command.Command;
import com.company.ibank.command.NoCommand;

import java.util.HashMap;
import java.util.Map;

public final class ActionHelper {

    private static Map<String, Command> commands;

    private static ActionHelper instance = new ActionHelper();

    private ActionHelper() {
        commands = new HashMap<String, Command>() {{
//            put(Command.PERSON_COMMAND, new PersonOpperationsCommand());
//            put(Command.BANK_COMMAND, new BankOpperationsViewCommand());
        }};
    }

    public Command getCommand(final String action) {
        Command command = commands.get(action);
        if (command == null) {
            command = new NoCommand();
        }
        return command;
    }

    public static ActionHelper getInstance() {
        return instance;
    }
}

