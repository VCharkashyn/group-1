package com.company.ibank.dao;

import com.company.ibank.model.Account;
import com.company.ibank.model.Currency;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Stateful
public class CurrencyDAOImpl implements CurrencyDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Currency currency) {
        if (currency != null) {
            currency.setCurrency(em.merge(currency).getCurrency());
        }
    }

    @Override
    public void remove(String currency) {
        em.remove(currency);
    }

    @Override
    public Currency findByName(String currency) {
        return em.find(Currency.class,currency);
    }

    @Override
    public List<Currency> getCurrencies() {
        CriteriaQuery<Currency> criteria = em.getCriteriaBuilder().createQuery(Currency.class);
        Root<Currency> currencies = criteria.from(Currency.class);
        criteria.select(currencies);
        return em.createQuery(criteria).getResultList();
    }
}
