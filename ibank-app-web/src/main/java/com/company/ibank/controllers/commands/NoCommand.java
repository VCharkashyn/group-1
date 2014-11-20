package com.company.ibank.controllers.commands;

import com.company.ibank.controllers.ICommand;
import com.company.ibank.controllers.RequestPage;
import com.company.ibank.utils.configurators.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoCommand implements ICommand {

    @Override
    public RequestPage execute(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        RequestPage page = new RequestPage();
        page.setPage(ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE));
        return page;
    }
}
