package com.company.ibank.services.local;

import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.Currency;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CurrencyService {
    void addCurrency(Currency currency) throws ServiceException;

    void removeCurrencies(String[] ids) throws ServiceException;

    void removeCurrency(Currency currency) throws ServiceException;

    Currency findCurrency(String currency);

    List<Currency> getAllCurrencies();
}
