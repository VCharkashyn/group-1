package com.company.ibank.services;

import com.company.ibank.exceptions.CurrencyNotFound;
import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.Currency;

import javax.annotation.security.RolesAllowed;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

public interface CurrencyService {
    @RolesAllowed("ibank")
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    void addCurrency(Currency currency) throws ServiceException;

    @RolesAllowed("ibank")
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    void removeCurrencies(String[] ids) throws ServiceException;

    @RolesAllowed("ibank")
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    void removeCurrency(Currency currency) throws ServiceException;

    @RolesAllowed("ibank")
    Currency findCurrency(String currency);

    @RolesAllowed("ibank")
    Currency findMainCurrency() throws CurrencyNotFound;

    @RolesAllowed("ibank")
    List<Currency> getAllCurrencies();
}
