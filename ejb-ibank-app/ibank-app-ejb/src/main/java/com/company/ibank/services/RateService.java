package com.company.ibank.services;

import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.Rate;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;

@Remote
public interface RateService {
    void addRate(Rate rate) throws ServiceException;

    void removeRate(String primCurrency, String secondCurrency, Date conversionDate) throws ServiceException;

    void removeRate(Rate rate) throws ServiceException;

    Rate findRate(String primCurrency, String secondCurrency, Date conversionDate) throws ServiceException;

    List<Rate> getRates() throws ServiceException;
}
