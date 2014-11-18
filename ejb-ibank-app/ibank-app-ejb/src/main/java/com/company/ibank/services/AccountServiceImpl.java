package com.company.ibank.services;


import com.company.ibank.dao.AccountDAO;
import com.company.ibank.exceptions.DAOException;
import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.Account;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import java.util.List;

@Stateful//rewrite to stateless
public class AccountServiceImpl implements AccountService{

    @EJB
    private AccountDAO accountDAO;

    @Override
    public Long openAccount(Account account) throws ServiceException {
        Long accountId;
        try {
            accountId = accountDAO.create(account);
        } catch (DAOException e) {
            throw new ServiceException("", e);
        }
        return accountId;
    }

    @Override
    public void closeAccount(Account account) throws ServiceException {
        try {
            accountDAO.remove(account);
        } catch (DAOException e) {
            throw new ServiceException("", e);
        }
    }

    @Override
    public Account findAccount(long id) throws ServiceException {
        Account account;
        try {
            account = accountDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException("", e);
        }
        return account;
    }

    @Override
    public List<Account> getAllAccounts() throws ServiceException {
        List<Account> accounts;
        try {
            accounts = accountDAO.getAccounts();
        } catch (DAOException e) {
            throw new ServiceException("", e);
        }
        return accounts;
    }
}
