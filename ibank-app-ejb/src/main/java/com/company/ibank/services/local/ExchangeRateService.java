package com.company.ibank.services.local;

import com.company.ibank.model.Account;

import javax.ejb.Local;
import java.math.BigDecimal;

@Local
public interface ExchangeRateService {
   BigDecimal exchangeCurrencies(Account account, String primaryCurrency, String secondaryCurrency);
}
