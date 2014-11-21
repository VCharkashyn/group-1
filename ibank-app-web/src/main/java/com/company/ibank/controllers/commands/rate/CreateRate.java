package com.company.ibank.controllers.commands.rate;


import com.company.ibank.controllers.ICommand;
import com.company.ibank.controllers.RequestPage;
import com.company.ibank.services.remote.RateServiceRemote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreateRate implements ICommand {
    private Logger log = LoggerFactory.getLogger(CreateRate.class);

    @EJB
    private RateServiceRemote rateService;

    @Override
    public RequestPage execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestPage page = new RequestPage();

        HttpSession session = request.getSession(false);

        return page;
    }
}
