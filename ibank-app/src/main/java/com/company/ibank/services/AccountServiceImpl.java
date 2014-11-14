package com.company.ibank.services;

import com.company.ibank.dao.AccountDAO;
import com.company.ibank.exceptions.AccountStateException;
import com.company.ibank.exceptions.DAOException;
import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;


public class AccountServiceImpl implements AccountService {
    private AccountDAO accountDAO;
    private ReentrantLock lock = new ReentrantLock();

    public AccountServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public Long createAccount(Person person, BigDecimal amount, Currency currency) throws ServiceException {
        if (person == null || amount == null || currency == null) {
            return null;
        }

        Long id;
        try {
            lock.lock();
            Account account = new Account();
            account.setMoney(new Money(amount, currency));
            account.setOwner(person);
            account.setStatus(AccountStatus.Active);
            id = accountDAO.createOrUpdateAccount(account);
        } catch (DAOException e) {
            throw new ServiceException("DAOException occurred", e);
        } finally {
            lock.unlock();
        }
        return id;
    }

    @Override
    public Long close(Long accountId) throws ServiceException {
        if (accountId == null) {
            return null;
        }

        Long id;
        try {
            lock.lock();
            Account account = accountDAO.findById(accountId);
            account.setStatus(AccountStatus.Close);
            id = accountDAO.createOrUpdateAccount(account);
        } catch (DAOException e) {
            throw new ServiceException("DAOException occurred", e);
        } finally {
            lock.unlock();
        }
        return id;
    }

    @Override
    public void freezeAccount(Long accountId) throws ServiceException {
        if (accountId == null) {
            throw new ServiceException("Unsupported parameter");
        }
        try {
            lock.lock();
            Account account = accountDAO.findById(accountId);
            if (AccountStatus.Frozen.equals(account.getStatus())) {
                throw new AccountStateException("Account was already frozen");
            }
            account.setStatus(AccountStatus.Frozen);
            accountDAO.createOrUpdateAccount(account);
        } catch (DAOException e) {
            throw new ServiceException("DAOException occurred", e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void unFreezeAccount(Long accountId) throws ServiceException {
        if (accountId == null) {
            throw new ServiceException("Unsupported parameter");
        }

        try {
            lock.lock();
            Account account = accountDAO.findById(accountId);
            if (AccountStatus.Active.equals(account.getStatus())) {
                throw new AccountStateException("Account was already activated");
            }
            account.setStatus(AccountStatus.Active);
            accountDAO.createOrUpdateAccount(account);
        } catch (DAOException e) {
            throw new ServiceException("DAOException occurred", e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Account find(Long accountId) throws ServiceException {
        if (accountId == null) {
            return null;
        }

        Account account;
        try {
            lock.lock();
            account = accountDAO.findById(accountId);
        } catch (DAOException e) {
            throw new ServiceException("DAOException occurred", e);
        } finally {
            lock.unlock();
        }
        return account;
    }

    @Override
    public List<Account> getAllAccounts() throws ServiceException {
        List<Account> accounts;

        try {
            lock.lock();
            accounts = accountDAO.getAccounts();
        } catch (DAOException e) {
            throw new ServiceException("DAOException occurred", e);
        } finally {
            lock.unlock();
        }
        return accounts;
    }
}
