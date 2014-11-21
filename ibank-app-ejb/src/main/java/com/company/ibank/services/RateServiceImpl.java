package com.company.ibank.services;

import com.company.ibank.dao.RateDAO;
import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.Currency;
import com.company.ibank.model.Rate;
import com.company.ibank.model.RatePK;
import com.company.ibank.services.local.RateServiceLocal;
import com.company.ibank.services.remote.RateServiceRemote;

import javax.annotation.security.DeclareRoles;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import java.util.Date;
import java.util.List;

@Stateless
@DeclareRoles("ibank")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class RateServiceImpl implements RateServiceRemote, RateServiceLocal {

    @EJB
    private RateDAO rateDAO;

    @Override
    public void addRate(final Rate rate) throws ServiceException {
        if (rate == null || rate.getId() == null) {
            throw new ServiceException("Unsupported parameter:rate");
        }
        rate.getId().setConversionDate(new Date());

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

    @Override
    public Rate findRate(Currency primCurrency, Currency secondCurrency) {
        if (primCurrency == null || secondCurrency == null) {
            return null;
        }

        return rateDAO.findRate(primCurrency, secondCurrency);
    }
}
