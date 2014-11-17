package com.company.ibank.dao;

import com.company.ibank.model.Currency;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CurrencyDAOImpl implements CurrencyDAO {
    private static final Map<String, Currency> storage = new ConcurrentHashMap<String, Currency>();

    @Override
    public void create(Currency currency) {
        if (currency != null && !storage.containsKey(currency.getCurrency())) {
            storage.put(currency.getCurrency(), currency);
        }
    }

    @Override
    public void remove(String currency) {
        storage.remove(currency);
    }

    @Override
    public Currency findByName(String currency) {
        return storage.get(currency);
    }

    @Override
    public List<Currency> getCurrencies() {
        return new ArrayList<Currency>(storage.values());
    }
}
