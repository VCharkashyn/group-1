package com.company.ibank.dao;

import com.company.ibank.exceptions.DAOException;
import com.company.ibank.model.Rate;
import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class RateDAOImpl implements RateDAO {
    private static final Map<String, Rate> storage = new ConcurrentHashMap<String, Rate>();

    @Override
    public void create(Rate rate) throws DAOException {
        if (rate == null || StringUtils.isBlank(rate.getPrimaryCurrency()) || StringUtils.isBlank(rate.getSecondaryCurrency()) || rate.getConversionDate() == null) {
            throw new DAOException("Unsupported rate object: can not persist");
        }

        storage.put(rate.getPrimaryCurrency() + rate.getSecondaryCurrency() + rate.getConversionDate().getTime(), rate);
    }

    @Override
    public void remove(String primCurrency, String secondCurrency, Date conversionDate) throws DAOException {
        if (StringUtils.isBlank(primCurrency) || StringUtils.isBlank(secondCurrency) || conversionDate == null) {
            throw new DAOException("Unsupported rate key: can not been deleted");
        }

        storage.remove(primCurrency + secondCurrency + conversionDate.getTime());
    }

    @Override
    public Rate findById(String primCurrency, String secondCurrency, Date conversionDate) throws DAOException {
        if (StringUtils.isBlank(primCurrency) || StringUtils.isBlank(secondCurrency) || conversionDate == null) {
            throw new DAOException("Unsupported rate key: can not been finded");
        }

        return storage.get(primCurrency + secondCurrency + conversionDate.getTime());
    }

    @Override
    public List<Rate> getRates() throws DAOException {
        return new ArrayList<Rate>(storage.values());
    }
}
