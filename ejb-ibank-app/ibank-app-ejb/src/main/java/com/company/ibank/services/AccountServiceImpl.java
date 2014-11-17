package com.company.ibank.services;


import com.company.ibank.dao.AccountDAO;
import com.company.ibank.exceptions.DAOException;
import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.Account;

import javax.ejb.EJB;
import java.util.List;

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
    public void closeAccount(long id) throws ServiceException {
        try {
            accountDAO.remove(id);
        } catch (DAOException e) {
            throw new ServiceException("", e);
        }
    }

    @Override
    public void closeAccount(Account account) throws ServiceException {
        closeAccount(account.getId());
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
