package com.company.ibank.dao;

import com.company.ibank.model.Currency;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CurrencyDAO {

    void create(Currency currency);

    void remove(Currency currency);

    int removeByIDs(String[] ids);

    Currency findByName(String currency);

    List<Currency> getCurrencies();
}
