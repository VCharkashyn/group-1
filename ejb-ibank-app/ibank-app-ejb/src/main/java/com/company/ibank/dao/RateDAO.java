package com.company.ibank.dao;

import com.company.ibank.exceptions.DAOException;
import com.company.ibank.model.Account;
import com.company.ibank.model.Rate;

import javax.ejb.Local;
import javax.ejb.Stateful;
import java.util.Date;
import java.util.List;

@Local
@Stateful
public interface RateDAO {

    void create(Rate rate) throws DAOException;

    void remove(String primCurrency, String secondCurrency, Date conversionDate) throws DAOException;

    Rate findById(String primCurrency, String secondCurrency, Date conversionDate) throws DAOException;

    List<Rate> getRates() throws DAOException;

}
