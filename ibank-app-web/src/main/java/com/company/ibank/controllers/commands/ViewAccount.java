package com.company.ibank.controllers.commands;

import com.company.ibank.controllers.ICommand;
import com.company.ibank.controllers.RequestPage;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewAccount implements ICommand {
    private final Logger log = Logger.getLogger(getClass());

    @Override
    public RequestPage execute(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        RequestPage page = new RequestPage();
        log.info("try to getsession");
        HttpSession session = request.getSession(false);

        return page;
    }
}
