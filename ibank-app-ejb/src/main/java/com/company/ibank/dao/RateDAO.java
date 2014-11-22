package com.company.ibank.dao;

import com.company.ibank.model.Currency;
import com.company.ibank.model.Rate;
import com.company.ibank.model.RatePK;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.Date;
import java.util.List;

@Local
public interface RateDAO {

    @RolesAllowed("ibank")
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    void create(Rate rate);

    @RolesAllowed("ibank")
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    void remove(Rate rate);

    @RolesAllowed("ibank")
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    Rate findById(RatePK id);

    @RolesAllowed("ibank")
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    List<Rate> getRates();

    @RolesAllowed("ibank")
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    Rate findRate(Currency primCurrency, Currency secondCurrency);

    @RolesAllowed("ibank")
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    Rate findRate(Currency primCurrency, Currency secondCurrency, Date date);
}
