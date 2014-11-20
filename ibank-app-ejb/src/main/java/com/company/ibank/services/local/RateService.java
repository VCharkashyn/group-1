package com.company.ibank.services.local;

import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.Rate;
import com.company.ibank.model.RatePK;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RateService {
    void addRate(Rate rate) throws ServiceException;

    void removeRate(Rate rate) throws ServiceException;

    Rate findRate(RatePK rateID);

    List<Rate> getRates();
}
