package com.company.ibank.dao;

import com.company.ibank.exceptions.DAOException;
import com.company.ibank.model.Currency;
import com.company.ibank.model.Rate;
import com.company.ibank.model.RatePK;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Stateful
public class RateDAOImpl implements RateDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Rate rate) throws DAOException {
        if (rate != null) {
            rate.setId(em.merge(rate).getId());
        }
    }

    @Override
    public void remove(Rate rate) throws DAOException {
        em.remove(rate);
    }

    @Override
    public Rate findById(RatePK id) throws DAOException {
        return em.find(Rate.class, id);
    }

    @Override
    public List<Rate> getRates() throws DAOException {
        CriteriaQuery<Rate> criteria = em.getCriteriaBuilder().createQuery(Rate.class);
        Root<Rate> rates = criteria.from(Rate.class);
        criteria.select(rates);
        return em.createQuery(criteria).getResultList();
    }
}
