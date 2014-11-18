package com.company.ibank.services;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Remote
public interface ExchangeRateService {
   void exchangeCurrencies();
}
