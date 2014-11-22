package com.company.ibank.dao;

import com.company.ibank.model.Currency;
import com.company.ibank.model.Rate;
import com.company.ibank.model.RatePK;

import javax.annotation.security.DeclareRoles;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@Stateless
@DeclareRoles("ibank")
@TransactionManagement(TransactionManagementType.CONTAINER)
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

    @Override
    public Rate findRate(final Currency primCurrency, final Currency secondCurrency) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Rate> criteria = criteriaBuilder.createQuery(Rate.class);
        Root<Rate> rates = criteria.from(Rate.class);
        CriteriaQuery<Rate> rateCriteriaQuery = criteria.select(rates).
                where(criteriaBuilder.and(criteriaBuilder.equal(rates.get("id").get("primaryCurrency"), primCurrency),
                        criteriaBuilder.equal(rates.get("id").get("secondaryCurrency"), secondCurrency)))
                .orderBy(criteriaBuilder.desc(rates.get("id").get("conversionDate")));

        final TypedQuery<Rate> query = entityManager.createQuery(rateCriteriaQuery);

        query.setMaxResults(1);
        final List<Rate> resultList = query.getResultList();
        if (resultList == null || resultList.size() == 0) {
            return null;
        }

        return resultList.get(0);
    }

    @Override
    public Rate findRate(final Currency primCurrency, final Currency secondCurrency, final Date date) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Rate> criteria = criteriaBuilder.createQuery(Rate.class);
        Root<Rate> rates = criteria.from(Rate.class);
        CriteriaQuery<Rate> rateCriteriaQuery = criteria.select(rates).
                where(criteriaBuilder.and(
                        criteriaBuilder.equal(rates.get("id").get("primaryCurrency"), primCurrency),
                        criteriaBuilder.equal(rates.get("id").get("secondaryCurrency"), secondCurrency),
                        criteriaBuilder.equal(rates.get("id").get("conversionDate"), date)
                ));

        final TypedQuery<Rate> query = entityManager.createQuery(rateCriteriaQuery);
        query.setMaxResults(1);
        final List<Rate> resultList = query.getResultList();
        if (resultList == null || resultList.size() == 0) {
            return null;
        }

        return resultList.get(0);
    }
}
