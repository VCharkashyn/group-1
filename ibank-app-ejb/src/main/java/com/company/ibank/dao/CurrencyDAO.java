package com.company.ibank.dao;

import com.company.ibank.model.Currency;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

@Local
public interface CurrencyDAO {

    @RolesAllowed("ibank")
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    void create(Currency currency);

    @RolesAllowed("ibank")
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    void remove(Currency currency);

    @RolesAllowed("ibank")
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    int removeByIDs(String[] ids);

    @RolesAllowed("ibank")
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    Currency findByName(String currency);

    @RolesAllowed("ibank")
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    List<Currency> getCurrencies();
}
