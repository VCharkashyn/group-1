package com.company.ibank.services;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Local
@Stateless
public interface ExchangeRateService {
   void exchangeCurrencies();
}
