package com.company.ibank.dao;

import com.company.ibank.model.Currency;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;


@Stateless
public class CurrencyDAOImpl implements CurrencyDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(final Currency currency) {
        entityManager.persist(currency);
    }

    @Override
    public void remove(final Currency currency) {
        entityManager.remove(currency);
    }


    @Override
    public int removeByIDs(final String[] ids) {
        if (ids == null) {
            return 0;
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete delete = criteriaBuilder.createCriteriaDelete(Currency.class);
        Root<Currency> root = delete.from(Currency.class);
        delete.where(root.get("currency").in(Arrays.asList(ids)));
        Query query = entityManager.createQuery(delete);
        return query.executeUpdate();
    }

    @Override
    public Currency findByName(final String currency) {
        if (StringUtils.isBlank(currency)) {
            return null;
        }

        return entityManager.find(Currency.class, currency);
    }

    @Override
    public List<Currency> getCurrencies() {
        CriteriaQuery<Currency> criteria = entityManager.getCriteriaBuilder().createQuery(Currency.class);
        Root<Currency> currencies = criteria.from(Currency.class);
        criteria.select(currencies);
        return entityManager.createQuery(criteria).getResultList();
    }
}
