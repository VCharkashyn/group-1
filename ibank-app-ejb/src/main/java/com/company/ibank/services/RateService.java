package com.company.ibank.services;

import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.Currency;
import com.company.ibank.model.Rate;
import com.company.ibank.model.RatePK;

import javax.annotation.security.RolesAllowed;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

public interface RateService {

    @RolesAllowed("ibank")
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    void addRate(Rate rate) throws ServiceException;

    @RolesAllowed("ibank")
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    void removeRate(Rate rate) throws ServiceException;

    @RolesAllowed("ibank")
    Rate findRate(RatePK rateID);

    @RolesAllowed("ibank")
    List<Rate> getRates();

    @RolesAllowed("ibank")
    Rate findRate(Currency primCurrency, Currency secondCurrency);
}
