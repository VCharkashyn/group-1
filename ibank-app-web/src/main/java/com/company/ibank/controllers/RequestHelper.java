package com.company.ibank.controllers;

import com.company.ibank.controllers.commands.NoCommand;
import com.company.ibank.controllers.commands.account.CreateAccount;
import com.company.ibank.controllers.commands.account.DeleteAccount;
import com.company.ibank.controllers.commands.account.ViewAllAccount;
import com.company.ibank.controllers.commands.currency.CreateCurrency;
import com.company.ibank.controllers.commands.currency.DeleteCurrency;
import com.company.ibank.controllers.commands.currency.ViewAllCurrency;
import com.company.ibank.controllers.commands.rate.CreateRate;
import com.company.ibank.controllers.commands.rate.DeleteRate;
import com.company.ibank.controllers.commands.rate.ViewRates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class RequestHelper {
    private static final Logger log = LoggerFactory.getLogger(RequestHelper.class);

    private Map<String, ICommand> commands =
            new HashMap<String, ICommand>();

    private RequestHelper() {
          commands.put("viewAllAccounts", new ViewAllAccount());
          commands.put("viewAllCurrency", new ViewAllCurrency());
          commands.put("viewAllRates", new ViewRates());

          commands.put("deleteAccount", new DeleteAccount());
          commands.put("deleteCurrency", new DeleteCurrency());
          commands.put("deleteRate", new DeleteRate());

          commands.put("createAccount", new CreateAccount());
          commands.put("createCurrency", new CreateCurrency());
          commands.put("createRate", new CreateRate());
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
