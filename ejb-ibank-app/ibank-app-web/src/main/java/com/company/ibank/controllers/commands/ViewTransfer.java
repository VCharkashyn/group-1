package com.company.ibank.controllers.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.ibank.controllers.ICommand;
import com.company.ibank.controllers.RequestPage;
import com.company.ibank.controllers.configurators.ConfigurationManager;
import org.apache.log4j.Logger;

public class ViewTransfer implements ICommand {
    private final Logger log = Logger.getLogger(getClass());

    @Override
    public RequestPage execute(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        RequestPage page = new RequestPage();
        log.info("get session");
        HttpSession session = request.getSession(false);
        log.info("set transfers to request");

        page.setPage(ConfigurationManager.getInstance().getProperty(ConfigurationManager.TRANSFERS_VIEW_PAGE));
        return page;
    }
}
