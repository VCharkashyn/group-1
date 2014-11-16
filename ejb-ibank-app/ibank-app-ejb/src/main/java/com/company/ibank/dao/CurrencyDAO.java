package com.company.ibank.dao;

import com.company.ibank.exceptions.DAOException;
import com.company.ibank.model.Currency;

import javax.ejb.Local;
import javax.ejb.Stateful;
import java.util.List;

@Local
@Stateful
public interface CurrencyDAO {

    void create(Currency currency) throws DAOException;

    void remove(String id) throws DAOException;

    Currency findByName(String currency) throws DAOException;

    List<Currency> getCurrencies() throws DAOException;
}
