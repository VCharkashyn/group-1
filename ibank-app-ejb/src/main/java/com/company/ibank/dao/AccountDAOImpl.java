package com.company.ibank.dao;


import com.company.ibank.model.Account;
import com.company.ibank.model.Currency;

import javax.annotation.security.DeclareRoles;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;


@Stateless
@DeclareRoles("ibank")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AccountDAOImpl implements AccountDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long create(final Account account) {
        if (account == null) {
            return null;
        }

        entityManager.persist(account);
        entityManager.flush();
        return account.getId();
    }

    @Override
    public void remove(final Account account) {
        entityManager.remove(account);
    }

    @Override
    public int removeByIDs(final Long[] ids) {
        if (ids == null) {
            return 0;
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete delete = criteriaBuilder.createCriteriaDelete(Account.class);
        Root root = delete.from(Account.class);
        delete.where(root.get("id").in(Arrays.asList(ids)));
        Query query = entityManager.createQuery(delete);
        return query.executeUpdate();
    }

    @Override
    public Account findById(final long id) {
        return entityManager.find(Account.class, id);
    }

    @Override
    public List<Account> getAccounts() {
        CriteriaQuery<Account> criteria = entityManager.getCriteriaBuilder().createQuery(Account.class);
        Root<Account> account = criteria.from(Account.class);
        criteria.select(account);
        return entityManager.createQuery(criteria).getResultList();
    }

    @Override
    public boolean isContainingCurrencies(final List<Currency> currencies, final Long id) {
        if(currencies == null || currencies.isEmpty() || id == null) {
            return false;
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteria = criteriaBuilder.createQuery();
        Join joinAccountCurrency = criteria.from(Account.class).join("currencies", JoinType.INNER);
        criteria.select(criteriaBuilder.count(joinAccountCurrency.get("currency")));
        criteria.where(criteriaBuilder.and(criteriaBuilder.equal(joinAccountCurrency.get("id"), id),
                joinAccountCurrency.get("currency").in(currencies)));

        Integer result = (Integer)entityManager.createQuery(criteria).getSingleResult();

        if (result != null && result.intValue()>1) {
            return true;
        }

        return false;
    }
}
