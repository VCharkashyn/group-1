package com.company.ibank.dao;

import com.company.ibank.exceptions.DAOException;
import com.company.ibank.model.Account;
import com.company.ibank.model.Rate;
import com.company.ibank.model.RatePK;

import javax.ejb.Local;
import javax.ejb.Stateful;
import java.util.Date;
import java.util.List;

@Local
public interface RateDAO {

    void create(Rate rate) throws DAOException;

    void remove(Rate rate) throws DAOException;

    Rate findById(RatePK id) throws DAOException;

    List<Rate> getRates() throws DAOException;

}
