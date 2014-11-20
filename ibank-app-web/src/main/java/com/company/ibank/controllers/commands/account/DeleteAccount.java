package com.company.ibank.controllers.commands.account;

import com.company.ibank.controllers.ICommand;
import com.company.ibank.controllers.RequestPage;
import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.exceptions.StringConversionException;
import com.company.ibank.services.remote.AccountServiceRemote;
import com.company.ibank.views.ViewUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteAccount implements ICommand {
    private Logger log = LoggerFactory.getLogger(DeleteAccount.class);

    @EJB
    private AccountServiceRemote accountService;


    @Override
    public RequestPage execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        RequestPage page = new RequestPage();

        String id = request.getParameter("id");

        if (StringUtils.isNotBlank(id)) {
            log.info("Deleting Account id"+ id);
            try {
                accountService.closeAccounts(new Long[]{ViewUtils.stringToLong(id)});
            }
            catch (StringConversionException e) {
               log.error("", e);
            }
        }

        page.setPage("");

        return page;
    }
}
