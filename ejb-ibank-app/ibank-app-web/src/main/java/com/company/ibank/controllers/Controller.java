package com.company.ibank.controllers;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class Controller extends HttpServlet
        implements javax.servlet.Servlet {

    private final Logger log = Logger.getLogger(getClass());
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
//                catch (AccesDenied ex) {
//                    log.error("acces exc", ex);
//                    request.setAttribute("errorMessage",
//                            MessageManager.getInstance((String) request.getAttribute("language")).getProperty(
//                            MessageManager.ACCESDENIED_EXCEPTION_ERROR_MESSAGE));
//                    page=new RequestPage();
//                    page.setPage(ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH));
//                    forward(page, request, response);
//                }
//                catch (DAOException ex) {
//                    log.error("dao exc", ex);
//                    request.setAttribute("errorMessage",
//                            MessageManager.getInstance((String) request.getAttribute("language")).getProperty(
//                            MessageManager.DAOEXCEPTION_ERROR_MESSAGE));
//                    page=new RequestPage();
//                    page.setPage(ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH));
//                    forward(page, request, response);
//                }
//                catch (DAOConfigurationException ex) {
//                    log.error("dao config exc", ex);
//                    request.setAttribute("errorMessage",
//                            MessageManager.getInstance((String) request.getAttribute("language")).getProperty(
//                            MessageManager.DAOCONFIGURATION_EXCEPTION_ERROR_MESSAGE));
//                    page=new RequestPage();
//                    page.setPage(ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH));
//                    forward(page, request, response);
//                }
//                catch (PoolException ex) {
//                    log.error("pool exc", ex);
//                    request.setAttribute("errorMessage",
//                            MessageManager.getInstance((String) request.getAttribute("language")).getProperty(
//                            MessageManager.POOLEXCEPTION_ERROR_MESSAGE));
//                    page=new RequestPage();
//                    page.setPage(ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH));
//                    forward(page, request, response);
//                }
//
//            }
//            catch (ServletException e) {
//                log.error("servlet exc:", e);
//                page=new RequestPage();
//                page.setPage(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SERVLET_ERROR_PAGE_PATH));
//                redirect(page, response);
//            }
//        }
        catch (IOException e) {
            log.error("io exc:", e);
        } catch (ServletException e) {


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
