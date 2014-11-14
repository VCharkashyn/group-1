package com.company.ibank.services;

import com.company.ibank.dao.AccountDAO;
import com.company.ibank.exceptions.DAOException;
import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.Account;
import com.company.ibank.model.Currency;
import com.company.ibank.model.Money;
import com.company.ibank.services.comparators.AccountSortedByCurrency;
import com.company.ibank.services.comparators.AccountSortedByName;
import com.company.ibank.utils.ExchangeCurrencyStore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class BankServiceImpl implements BankService {
    private AccountDAO accountDao;
    private ReentrantLock lock = new ReentrantLock();

    public BankServiceImpl(AccountDAO accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void exchange(Long id, Currency currency) throws ServiceException {
        if (id == null && currency == null) {
            throw new ServiceException("Unsupported parameter");
        }

        try {
            lock.lock();
            Account accountById = accountDao.findById(id);
            if (accountById != null) {
                Money money = accountById.getMoney();
                Currency currencyFromAccount = money.getCurrency();
                if (currencyFromAccount.equals(currency)) {
                    System.out.println("Currency is the same to exchange");
                    //low.warn
                } else {
                    String currencyName = currencyFromAccount.getCurrencyName();
                    String currencyNameTo = currency.getCurrencyName();
                    if (ExchangeCurrencyStore.EURO.equals(currencyName)) {
                        if (ExchangeCurrencyStore.BYR.equals(currencyNameTo)) {
                            accountById.setMoney(new Money(money.getAmount().multiply(ExchangeCurrencyStore.EUR2BYR), Currency.BYR));
                        } else {
                            accountById.setMoney(new Money(money.getAmount().multiply(ExchangeCurrencyStore.EUR2USD), Currency.USD));
                        }
                    } else if (currencyName.equals(ExchangeCurrencyStore.BYR)) {
                        if (ExchangeCurrencyStore.USD.equals(currencyNameTo)) {
                            accountById.setMoney(new Money(money.getAmount().multiply(ExchangeCurrencyStore.BYR2USD), Currency.USD));
                        } else {
                            accountById.setMoney(new Money(money.getAmount().multiply(ExchangeCurrencyStore.BYR2EUR), Currency.EURO));
                        }
                    } else {
                        if (ExchangeCurrencyStore.EURO.equals(currencyNameTo)) {
                            accountById.setMoney(new Money(money.getAmount().multiply(ExchangeCurrencyStore.USD2EUR), Currency.EURO));
                        } else {
                            accountById.setMoney(new Money(money.getAmount().multiply(ExchangeCurrencyStore.USD2BYR), Currency.BYR));
                        }
                    }
                }
            }
            accountDao.createOrUpdateAccount(accountById);
        } catch (DAOException e) {
            throw new ServiceException("DAOException occurred", e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public List<Account> filterByName(String name, String surname) throws ServiceException {
        List<Account> accounts = Collections.emptyList();
        try {
            lock.lock();
            accounts = accountDao.getAccounts();
            if (accounts == null || accounts.isEmpty()) {
                return Collections.emptyList();
            }
            List<Account> list = new ArrayList<Account>(accounts.size());
            for (Account account : accounts) {
                if (account.getOwner() != null && (account.getOwner().getName().equals(name) && account.getOwner().getSurname().equals(surname))) {
                    list.add(account);
                }
            }
        } catch (DAOException e) {
            throw new ServiceException("DAOException occurred", e);
        } finally {
            lock.unlock();
        }
        return accounts;
    }

    @Override
    public List<Account> filterByCurrency(Currency currency) throws ServiceException {
        List<Account> accounts = Collections.emptyList();
        try {
            lock.lock();
            accounts = accountDao.getAccounts();
            if (accounts == null || accounts.isEmpty()) {
                return Collections.emptyList();
            }
            List<Account> list = new ArrayList<Account>(accounts.size());
            for (Account account : accounts) {
                if (account.getMoney() != null && account.getMoney().getCurrency().equals(currency)) {
                    list.add(account);
                }
            }
        } catch (DAOException e) {
            throw new ServiceException("DAOException occurred", e);
        } finally {
            lock.unlock();
        }
        return accounts;
    }

    @Override
    public List<Account> sortByName() throws ServiceException {
        List<Account> accounts = Collections.emptyList();
        try {
            lock.lock();
            accounts = accountDao.getAccounts();
            if (accounts == null || accounts.isEmpty()) {
                return Collections.emptyList();
            }
            Collections.sort(accounts, new AccountSortedByName());
        } catch (DAOException e) {
            throw new ServiceException("DAOException occurred", e);
        } finally {
            lock.unlock();
        }
        return accounts;
    }

    @Override
    public List<Account> sortByCurrency() throws ServiceException {
        List<Account> accounts = Collections.emptyList();
        try {
            lock.lock();
            accounts = accountDao.getAccounts();
            if (accounts == null || accounts.isEmpty()) {
                return Collections.emptyList();
            }
            Collections.sort(accounts, new AccountSortedByCurrency());
        } catch (DAOException e) {
            throw new ServiceException("DAOException occurred", e);
        } finally {
            lock.unlock();
        }
        return accounts;
    }
}
