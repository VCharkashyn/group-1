package com.company.ibank.services.local;

import com.company.ibank.dao.CurrencyDAO;
import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.Currency;

import javax.ejb.EJB;
import java.util.List;

public class CurrencyServiceImpl implements CurrencyService {

    @EJB
    private CurrencyDAO currencyDAO;

    @Override
    public void addCurrency(final Currency currency) throws ServiceException {
        if (currency == null) {
            throw new ServiceException("Unsupported parameter:currency");
        }

        currencyDAO.create(currency);
    }

    @Override
    public void removeCurrencies(final String[] ids) throws ServiceException {
        if (ids == null) {
            throw new ServiceException("Unsupported parameter:currency ids");
        }

        currencyDAO.removeByIDs(ids);
    }

    @Override
    public void removeCurrency(final Currency currency) throws ServiceException {
        if (currency == null) {
            throw new ServiceException("Unsupported parameter:currency");
        }

        currencyDAO.remove(currency);
    }

    @Override
    public Currency findCurrency(final String currency) {
        if (currency == null) {
            return null;
        }

        return currencyDAO.findByName(currency);
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyDAO.getCurrencies();
    }
}
