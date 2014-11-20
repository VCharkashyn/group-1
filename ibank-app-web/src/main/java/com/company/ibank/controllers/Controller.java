package com.company.ibank.controllers;

import com.company.ibank.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet
        implements javax.servlet.Servlet {

    private static final Logger log = LoggerFactory.getLogger(Controller.class);
    RequestHelper requestHelper =
            RequestHelper.getInstance();

    public Controller() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        log.info("doGet url:" + request.getRequestURL());
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        log.info("doGet url:" + request.getRequestURL());
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        RequestPage page = null;
        try {
            log.info("command start");
            ICommand command =
                    requestHelper.getCommand(request);
            log.info("command get:" + command.toString());
            page = command.execute(request, response);
            if (page.isRedericted()) {
                redirect(page, response);
            } else {
                forward(page, request, response);
            }
        }
        catch (ServiceException e) {
            log.error("", e);
        }
        catch (ServletException e) {
            log.error("", e);
        }
        catch (IOException e) {
            log.error("io exc:", e);
        }
    }

    private void redirect(RequestPage aDestinationPage, HttpServletResponse aResponse) throws IOException {
        String urlWithSessionID = aResponse.encodeRedirectURL(aDestinationPage.toString());
        aResponse.sendRedirect(urlWithSessionID);
    }

    private void forward(RequestPage aResponsePage, HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {
        RequestDispatcher dispatcher = aRequest.getRequestDispatcher(aResponsePage.toString());
        dispatcher.forward(aRequest, aResponse);
    }
}
