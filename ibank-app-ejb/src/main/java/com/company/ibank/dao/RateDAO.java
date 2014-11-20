package com.company.ibank.dao;

import com.company.ibank.model.Rate;
import com.company.ibank.model.RatePK;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RateDAO {

    void create(Rate rate);

    void remove(Rate rate);

    Rate findById(RatePK id);

    List<Rate> getRates();

}
