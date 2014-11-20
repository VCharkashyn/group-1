package com.company.ibank.controllers.commands.currency;


import com.company.ibank.controllers.ICommand;
import com.company.ibank.controllers.RequestPage;
import com.company.ibank.services.remote.CurrencyServiceRemote;
import com.company.ibank.utils.configurators.ConfigurationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewAllCurrency implements ICommand {
    private Logger log = LoggerFactory.getLogger(ViewAllCurrency.class);

    @EJB
    private CurrencyServiceRemote currencyService;

    @Override
    public RequestPage execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestPage page = new RequestPage();
        request.setAttribute("currencies",currencyService.getAllCurrencies());
        page.setPage(ConfigurationManager.getInstance().getProperty("currencies"));
        return page;
    }
}
