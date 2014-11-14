package com.company.ibank.model;


import com.company.ibank.utils.ExchangeCurrencyStore;

import java.util.HashMap;
import java.util.Map;

public enum Currency {
    EURO(ExchangeCurrencyStore.EURO), USD(ExchangeCurrencyStore.USD), BYR(ExchangeCurrencyStore.BYR);
    private static Map<String, Currency> lookup = new HashMap<String, Currency>();
    private String currencyName;

    private Currency(final String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public static Currency valueOfCurrency(final String name) {
        for (Currency currency : Currency.values()) {
            lookup.put(currency.getCurrencyName(), currency);
        }
        return lookup.get(name);
    }
}
