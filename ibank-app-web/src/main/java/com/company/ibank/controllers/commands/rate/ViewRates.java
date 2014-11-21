package com.company.ibank.controllers.commands.rate;


import com.company.ibank.controllers.ICommand;
import com.company.ibank.controllers.RequestPage;
import com.company.ibank.services.remote.RateServiceRemote;
import com.company.ibank.utils.configurators.ConfigurationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewRates implements ICommand{
    private Logger log = LoggerFactory.getLogger(ViewRates.class);

    @EJB
    private RateServiceRemote rateService;

    @Override
    public RequestPage execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestPage page = new RequestPage();
        request.setAttribute("rates", rateService.getRates());
        page.setPage(ConfigurationManager.getInstance().getProperty("view-rates"));
        return page;
    }
}
