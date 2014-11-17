package com.company.ibank.services;

import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.Currency;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Local
@Stateless
public interface CurrencyService {
    void addCurrency(Currency currency) throws ServiceException;

    void removeCurrency(String id) throws ServiceException;

    void removeCurrency(Currency currency) throws ServiceException;

    Currency findCurrency(String currency) throws ServiceException;

    List<Currency> getAllCurrencies() throws ServiceException;
}
