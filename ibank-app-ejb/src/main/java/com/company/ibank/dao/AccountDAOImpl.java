package com.company.ibank.dao;


import com.company.ibank.model.Account;

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
}
