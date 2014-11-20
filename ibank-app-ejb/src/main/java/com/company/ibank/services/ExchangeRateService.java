package com.company.ibank.services;

import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.Account;

import javax.annotation.security.RolesAllowed;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.math.BigDecimal;

public interface ExchangeRateService {

   @RolesAllowed("ibank")
   @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
   BigDecimal exchangeCurrencies(Account account, String primaryCurrency, String secondaryCurrency) throws ServiceException;
}
