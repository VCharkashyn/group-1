package com.company.ibank.controllers;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.company.ibank.controllers.commands.NoCommand;
import com.company.ibank.controllers.commands.ViewAccount;
import org.apache.log4j.Logger;

public class RequestHelper {
    private static final Logger log = Logger.getLogger(RequestHelper.class);
    private Map<String, ICommand> commands =
            new HashMap<String, ICommand>();

    private RequestHelper() {
          commands.put("viewAccount", new ViewAccount());
    }

    public ICommand getCommand(HttpServletRequest request) {
        String action = request.getParameter("command");
        ICommand command = commands.get(action);
        if (command == null) {
            command = new NoCommand();
            log.info("no command execute");
        }
        return command;
    }

    public static RequestHelper getInstance() {
        return RequestHelperHolder.INSTANCE;
    }

    public static class RequestHelperHolder {
        public static final RequestHelper INSTANCE = new RequestHelper();
    }
}
