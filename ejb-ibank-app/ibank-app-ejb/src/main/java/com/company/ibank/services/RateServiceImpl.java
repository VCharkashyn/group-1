package com.company.ibank.services;

import com.company.ibank.dao.RateDAO;
import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.Rate;

import javax.ejb.EJB;
import java.util.Date;
import java.util.List;

public class RateServiceImpl implements RateService {

    @EJB
    private RateDAO rateDAO;


    @Override
    public void addRate(Rate rate) throws ServiceException {

    }

    @Override
    public void removeRate(String primCurrency, String secondCurrency, Date conversionDate) throws ServiceException {

    }

    @Override
    public void removeRate(Rate rate) throws ServiceException {

    }

    @Override
    public Rate findRate(String primCurrency, String secondCurrency, Date conversionDate) throws ServiceException {
        return null;
    }

    @Override
    public List<Rate> getRates() throws ServiceException {
        return null;
    }
}
