package com.company.ibank.dao;

import com.company.ibank.exceptions.DAOException;
import com.company.ibank.model.Currency;

import java.util.List;

public interface CurrencyDAO {

    void create(Currency currency) throws DAOException;

    void remove(long id) throws DAOException;

    Currency findByName(String currency) throws DAOException;

    List<Currency> getCurrencies() throws DAOException;
}
