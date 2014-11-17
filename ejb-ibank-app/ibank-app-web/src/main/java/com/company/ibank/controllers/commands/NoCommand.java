package com.company.ibank.controllers.commands;

import com.company.ibank.controllers.ICommand;
import com.company.ibank.controllers.RequestPage;
import com.company.ibank.controllers.configurators.ConfigurationManager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoCommand implements ICommand {

    @Override
    public RequestPage execute(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        RequestPage page = new RequestPage();
        page.setPage(ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_URL));
        return page;
    }
}
