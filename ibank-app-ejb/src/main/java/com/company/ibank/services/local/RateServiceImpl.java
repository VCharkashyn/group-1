package com.company.ibank.services.local;

import com.company.ibank.dao.RateDAO;
import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.Rate;
import com.company.ibank.model.RatePK;

import javax.ejb.EJB;
import java.util.List;

public class RateServiceImpl implements RateService {

    @EJB
    private RateDAO rateDAO;

    @Override
    public void addRate(final Rate rate) throws ServiceException {
        if (rate == null) {
            throw new ServiceException("Unsupported parameter:rate");
        }
        RatePK ratePK = new RatePK();
        ratePK.

        rate.setId();

        rateDAO.create(rate);
    }

    @Override
    public void removeRate(final Rate rate) throws ServiceException {
        if (rate == null) {
            throw new ServiceException("Unsupported parameter:rate");
        }

        rateDAO.remove(rate);
    }

    @Override
    public Rate findRate(final RatePK rateID) {
        if (rateID == null) {
            return null;
        }

        return rateDAO.findById(rateID);
    }

    @Override
    public List<Rate> getRates() {
        return rateDAO.getRates();
    }
}
