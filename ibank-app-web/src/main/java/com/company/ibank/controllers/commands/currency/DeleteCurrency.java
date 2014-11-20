package com.company.ibank.controllers.commands.currency;


import com.company.ibank.controllers.ICommand;
import com.company.ibank.controllers.RequestPage;
import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.services.remote.CurrencyServiceRemote;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCurrency implements ICommand {
    private Logger log = LoggerFactory.getLogger(DeleteCurrency.class);

    @EJB
    private CurrencyServiceRemote currencyService;

    @Override
    public RequestPage execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        RequestPage page = new RequestPage();

        String id = request.getParameter("currency");

        if (StringUtils.isNotBlank(id)) {
            log.info("Deleting Currency"+ id);
            currencyService.removeCurrencies(new String[] {id});
        }

        page.setPage("");
        return page;
    }
}
