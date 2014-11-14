package com.company.ibank.services;

import com.company.ibank.AbstractTestBase;
import com.company.ibank.dao.AccountDAO;
import com.company.ibank.exceptions.DAOException;
import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.*;
import com.company.ibank.utils.ExchangeCurrencyStore;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BankServiceImplTest extends AbstractTestBase {

    @Mock
    private AccountDAO accountDao;
    private BankService bankService;
    private Account account;
    private Account accountTwo;

    @Before
    public void setup() {
        account = new Account();
        account.setOwner(new Person("Ivan", "Ivanov"));
        account.setMoney(new Money(new BigDecimal(5.0), Currency.EURO));
        account.setStatus(AccountStatus.Active);
        account.setId(new Long(3657));

        accountTwo = new Account();
        accountTwo.setOwner(new Person("Sveta", "Kapustina"));
        accountTwo.setMoney(new Money(new BigDecimal(7.0), Currency.USD));
        accountTwo.setStatus(AccountStatus.Active);
        accountTwo.setId(new Long(3659));

        bankService = new BankServiceImpl(accountDao);
    }

    @Test
    public void testExchange() throws ServiceException, DAOException {
        getContext().checking(new Expectations() {
            {
                oneOf(accountDao).findById(new Long(3657));
                will(returnValue(account));

                oneOf(accountDao).createOrUpdateAccount(with(any(Account.class)));
            }
        });

        bankService.exchange(new Long(3657), Currency.USD);
        assertEquals(ExchangeCurrencyStore.USD, account.getMoney().getCurrency().getCurrencyName());
    }

    @Test(expected = ServiceException.class)
    public void testExchangeWithNull() throws ServiceException, DAOException {
        bankService.exchange(null, null);
    }

    @Test(expected = ServiceException.class)
    public void testExchangeWithException() throws ServiceException, DAOException {
        getContext().checking(new Expectations() {
            {
                oneOf(accountDao).findById(new Long(3657));
                will(returnValue(account));

                oneOf(accountDao).createOrUpdateAccount(with(any(Account.class)));
                will(throwException(new DAOException("")));
            }
        });

        bankService.exchange(new Long(3657), Currency.USD);
    }

    @Test
    public void testFilterByNameWithNoAccounts() throws ServiceException, DAOException {
        getContext().checking(new Expectations() {
            {
                oneOf(accountDao).getAccounts();
                will(returnValue(Collections.emptyList()));

            }
        });

        assertNotNull(bankService.filterByName("ghj", "cvb"));
    }

    @Test(expected = ServiceException.class)
    public void testFilterByNameWithException() throws ServiceException, DAOException {
        getContext().checking(new Expectations() {
            {
                oneOf(accountDao).getAccounts();
                will(throwException(new DAOException("I/O exception")));
            }
        });

        bankService.filterByName("Ivan", "Ivanov");
    }

    @Test
    public void testFilterByName() throws ServiceException, DAOException {
        final List<Account> result2 = new ArrayList<Account>();
        result2.add(accountTwo);
        result2.add(account);
        result2.add(account);

        getContext().checking(new Expectations() {
            {
                oneOf(accountDao).getAccounts();
                will(returnValue(result2));

            }
        });

        List<Account> accounts = bankService.filterByName("Ivan", "Ivanov");
        assertTrue(accounts.size()==2);
        assertTrue("Ivan".equals(accounts.get(0).getOwner().getName()) && "Ivanov".equals(accounts.get(0).getOwner().getSurname()));
    }


    @Test
    public void testFilterByCurrency() throws ServiceException, DAOException {

        final List<Account> result2 = new ArrayList<Account>();
        result2.add(accountTwo);
        result2.add(account);
        result2.add(account);

        getContext().checking(new Expectations() {
            {
                oneOf(accountDao).getAccounts();
                will(returnValue(result2));

            }
        });

        List<Account> accounts = bankService.filterByCurrency(Currency.USD);
        assertTrue(accounts.size()==1);
        assertTrue(ExchangeCurrencyStore.USD.equals(accounts.get(0).getMoney().getCurrency().getCurrencyName()));
    }


    @Test(expected = ServiceException.class)
    public void testFilterByCurrencyWithException() throws ServiceException, DAOException {

        getContext().checking(new Expectations() {
            {
                oneOf(accountDao).getAccounts();
                will(throwException(new DAOException("I/O exception")));
            }
        });

        List<Account> accounts = bankService.filterByCurrency(Currency.USD);
    }

    @Test
    public void testFilterByCurrencyWithNull() throws ServiceException, DAOException {

        getContext().checking(new Expectations() {
            {
                oneOf(accountDao).getAccounts();
                will(returnValue(Collections.emptyList()));
            }
        });

        assertTrue(bankService.filterByCurrency(Currency.USD).isEmpty());
    }



    @Test
    public void testSortByName() throws DAOException, ServiceException {
        final List<Account> result2 = new ArrayList<Account>();
        result2.add(accountTwo);
        result2.add(account);
        result2.add(account);

        getContext().checking(new Expectations() {
            {
                oneOf(accountDao).getAccounts();
                will(returnValue(result2));

            }
        });

        List<Account> accounts = bankService.sortByName();
        assertTrue("Ivan".equals(accounts.get(0).getOwner().getName()) && "Ivanov".equals(accounts.get(0).getOwner().getSurname()));
    }

    @Test(expected = ServiceException.class)
    public void testSortByNameWithException() throws DAOException, ServiceException {
        getContext().checking(new Expectations() {
            {
                oneOf(accountDao).getAccounts();
                will(throwException(new DAOException("")));
            }
        });

        List<Account> accounts = bankService.sortByName();
    }

    @Test
    public void testSortByCurrency() throws DAOException, ServiceException {

        final List<Account> result2 = new ArrayList<Account>();
        result2.add(accountTwo);
        result2.add(account);
        result2.add(account);

        getContext().checking(new Expectations() {
            {
                oneOf(accountDao).getAccounts();
                will(returnValue(result2));
            }
        });

        List<Account> accounts = bankService.sortByCurrency();

        assertEquals(ExchangeCurrencyStore.EURO, accounts.get(0).getMoney().getCurrency().getCurrencyName());
    }

    @Test
    public void testSortByCurrencyEmptyAccounts() throws DAOException, ServiceException {

        getContext().checking(new Expectations() {
            {
                oneOf(accountDao).getAccounts();
                will(returnValue(null));
            }
        });

        assertTrue(bankService.sortByCurrency().isEmpty());
    }
}
