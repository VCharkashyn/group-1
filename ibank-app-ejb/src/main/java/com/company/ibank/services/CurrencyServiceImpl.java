package com.company.ibank.services;

import com.company.ibank.dao.CurrencyDAO;
import com.company.ibank.exceptions.CurrencyNotFound;
import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.Currency;
import com.company.ibank.services.local.CurrencyServiceLocal;
import com.company.ibank.services.remote.CurrencyServiceRemote;
import com.company.ibank.utils.configurators.ConfigurationManager;

import javax.annotation.security.DeclareRoles;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import java.util.List;

@Stateless
@DeclareRoles("ibank")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CurrencyServiceImpl implements CurrencyServiceLocal, CurrencyServiceRemote {
    private static final String MAIN_CURRENCY_KEY = "MAIN_CURRENCY";//usd

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
    public Currency findMainCurrency() throws CurrencyNotFound {
        final String currency = ConfigurationManager.getInstance().getProperty(MAIN_CURRENCY_KEY);
        final Currency mainCurrency = currencyDAO.findByName(currency);

        if (mainCurrency == null) {
            throw new CurrencyNotFound("The main currency is not found");
        }

        return mainCurrency;
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyDAO.getCurrencies();
    }
}
