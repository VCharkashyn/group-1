package com.company.ibank.views.filters;

import java.io.IOException;
import java.util.Locale;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.log4j.Logger;

public class Internationalization implements Filter {
    private final static Logger log = Logger.getLogger(Internationalization.class);

    /**
     * Sets to request browser locale
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        log("Internationalization:doFilter()");
        Locale locale = request.getLocale();
        String language = locale.toString();
        request.setAttribute("language", language);
        chain.doFilter(request, response);
    }

    /**
     * Destroy method for this filter 
     */
    @Override
    public void destroy() {
    }

    /**
     * Init method for this filter 
     */
    @Override
    public void init(FilterConfig filterConfig) {
    }

    public void log(String msg) {
        log.info(msg);
    }
}
