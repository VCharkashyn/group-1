package com.company.ibank.dao;


import com.company.ibank.model.Account;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

@Stateful
public class AccountDAOImpl implements AccountDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long create(Account account) {
        if (account != null) {
            Account savedAccount = em.merge(account);
            Long id = savedAccount.getId();
            account.setId(id);
            return id;
        }

        return null;
    }

    @Override
    public void remove(Account account) {
        em.remove(account);
    }

    @Override
    public Account findById(long id) {
        return em.find(Account.class, id);
    }

    @Override
    public List<Account> getAccounts() {
        CriteriaQuery<Account> criteria = em.getCriteriaBuilder().createQuery(Account.class);
        Root<Account> account = criteria.from(Account.class);
        criteria.select(account);
        return em.createQuery(criteria).getResultList();
    }
}
