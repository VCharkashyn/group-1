package com.company.ibank.services;

import com.company.ibank.dao.CurrencyDAO;
import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.Currency;

import javax.ejb.EJB;
import java.util.List;

public class CurrencyServiceImpl implements CurrencyService {

    @EJB
    private CurrencyDAO currencyDAO;

    @Override
    public void addCurrency(Currency currency) throws ServiceException {

    }

    @Override
    public void removeCurrency(String id) throws ServiceException {

    }

    @Override
    public void removeCurrency(Currency currency) throws ServiceException {

    }

    @Override
    public Currency findCurrency(String currency) throws ServiceException {
        return null;
    }

    @Override
    public List<Currency> getAllCurrencies() throws ServiceException {
        return null;
    }
}
