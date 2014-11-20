package com.company.ibank.controllers.commands.account;

import com.company.ibank.controllers.ICommand;
import com.company.ibank.controllers.RequestPage;
import com.company.ibank.services.remote.AccountServiceRemote;
import com.company.ibank.utils.configurators.ConfigurationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewAllAccount implements ICommand {
    private Logger log = LoggerFactory.getLogger(ViewAllAccount.class);

    @EJB
    private AccountServiceRemote accountService;


    @Override
    public RequestPage execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestPage page = new RequestPage();
        request.setAttribute("accounts", accountService.getAllAccounts());
        page.setPage(ConfigurationManager.getInstance().getProperty("view-accounts"));
        return page;
    }
}
