package com.company.ibank.dao;

import com.company.ibank.model.Rate;
import com.company.ibank.model.RatePK;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class RateDAOImpl implements RateDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(final Rate rate) {
        entityManager.persist(rate);
    }

    @Override
    public void remove(final Rate rate) {
        entityManager.remove(rate);
    }

    @Override
    public Rate findById(final RatePK id) {
        return entityManager.find(Rate.class, id);
    }

    @Override
    public List<Rate> getRates() {
        CriteriaQuery<Rate> criteria = entityManager.getCriteriaBuilder().createQuery(Rate.class);
        Root<Rate> rates = criteria.from(Rate.class);
        criteria.select(rates);
        return entityManager.createQuery(criteria).getResultList();
    }
}
