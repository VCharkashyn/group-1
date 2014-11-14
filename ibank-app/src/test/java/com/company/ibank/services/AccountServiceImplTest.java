package com.company.ibank.services;

import com.company.ibank.AbstractTestBase;
import com.company.ibank.dao.AccountDAO;
import com.company.ibank.exceptions.AccountStateException;
import com.company.ibank.exceptions.DAOException;
import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.*;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AccountServiceImplTest extends AbstractTestBase {

    @Mock
    private AccountDAO accountDAO;
    private AccountService accountService;
    private Account account;

    @Before
    public void setup() {
        account = new Account();
        account.setOwner(new Person("Ivan", "Ivanov"));
        account.setMoney(new Money(new BigDecimal(5.0), Currency.EURO));
        account.setStatus(AccountStatus.Active);
        account.setId(new Long(3657));
        accountService = new AccountServiceImpl(accountDAO);
    }

    @Test
    public void testCreateAccountWithNull() throws ServiceException {
        assertNull(accountService.createAccount(null, null, null));
    }

    @Test
    public void testCreateAccount() throws ServiceException, DAOException {
        getContext().checking(new Expectations() {
            {
                oneOf(accountDAO).createOrUpdateAccount(with(any(Account.class)));
                will(returnValue(account.getId()));
            }
        });

        assertNotNull(accountService.createAccount(account.getOwner(), account.getMoney().getAmount(), account.getMoney().getCurrency()));
    }

    @Test(expected = ServiceException.class)
    public void testCreateAccountWithException() throws ServiceException, DAOException {
        getContext().checking(new Expectations() {
            {
                oneOf(accountDAO).createOrUpdateAccount(with(any(Account.class)));
                will(throwException(new DAOException("I/O exception")));
            }
        });

        accountService.createAccount(account.getOwner(), account.getMoney().getAmount(), account.getMoney().getCurrency());
    }

    @Test
    public void testClose() throws ServiceException, DAOException {
        getContext().checking(new Expectations() {
            {
                oneOf(accountDAO).findById(account.getId());
                will(returnValue(account));

                oneOf(accountDAO).createOrUpdateAccount(account);
                will(returnValue(account.getId()));
            }
        });

        assertEquals(account.getId(), accountService.close(account.getId()));
    }

    @Test
    public void testCloseWithNull() throws ServiceException, DAOException {
        assertNull(accountService.close(null));
    }

    @Test(expected = ServiceException.class)
    public void testCloseWithException() throws Exception {
        getContext().checking(new Expectations() {
            {
                oneOf(accountDAO).findById(account.getId());
                will(returnValue(account));

                oneOf(accountDAO).createOrUpdateAccount(account);
                will(throwException(new DAOException("blah blah blah...")));
            }
        });

        accountService.close(account.getId());
    }


    @Test(expected = ServiceException.class)
    public void testFreezeAccountWithException() throws ServiceException {
        accountService.freezeAccount(null);
    }

    @Test(expected = AccountStateException.class)
    public void testFreezeAccountWithFreezeAcc() throws ServiceException, DAOException {
        account.setStatus(AccountStatus.Frozen);
        getContext().checking(new Expectations() {
            {
                oneOf(accountDAO).findById(account.getId());
                will(returnValue(account));
            }
        });

        accountService.freezeAccount(account.getId());
    }

    @Test
    public void testFreezeAccount() throws ServiceException, DAOException {
        getContext().checking(new Expectations() {
            {
                oneOf(accountDAO).findById(account.getId());
                will(returnValue(account));

                oneOf(accountDAO).createOrUpdateAccount(account);
            }
        });

        accountService.freezeAccount(account.getId());
        assertEquals("frozen", account.getStatus().getAccountStatus());
    }

    @Test
    public void testUnFreezeAccount() throws ServiceException, DAOException {
        account.setStatus(AccountStatus.Frozen);
        getContext().checking(new Expectations() {
            {
                oneOf(accountDAO).findById(account.getId());
                will(returnValue(account));

                oneOf(accountDAO).createOrUpdateAccount(account);
            }
        });

        accountService.unFreezeAccount(account.getId());
        assertEquals("active", account.getStatus().getAccountStatus());
    }

    @Test(expected = AccountStateException.class)
    public void testUnFreezeAccountWithException() throws ServiceException, DAOException {
        getContext().checking(new Expectations() {
            {
                oneOf(accountDAO).findById(account.getId());
                will(returnValue(account));
            }
        });

        accountService.unFreezeAccount(account.getId());
    }

    @Test(expected = ServiceException.class)
    public void testUnFreezeAccountWithServiceException() throws ServiceException, DAOException {
        accountService.unFreezeAccount(null);
    }

    @Test
    public void testFind() throws DAOException, ServiceException {
        getContext().checking(new Expectations() {
            {
                oneOf(accountDAO).findById(account.getId());
                will(returnValue(account));
            }
        });

        accountService.find(account.getId());
    }

    @Test(expected = ServiceException.class)
    public void testFindWithException() throws ServiceException, DAOException {
        getContext().checking(new Expectations() {
            {
                oneOf(accountDAO).findById(account.getId());
                will(throwException(new DAOException("I/O exception")));
            }
        });

        accountService.find(account.getId());
    }

    @Test
    public void testFindWithNull() throws ServiceException {
        assertNull(accountService.find(null));
    }

    @Test
    public void testGetAllAccounts() throws DAOException, ServiceException {
        final List result = new ArrayList<Account>();
        result.add(account);
        getContext().checking(new Expectations() {
            {
                oneOf(accountDAO).getAccounts();
                will(returnValue(result));
            }
        });

        List<Account> allAccounts = accountService.getAllAccounts();
        assertNotNull(allAccounts);
        assertFalse(allAccounts.isEmpty());
    }
}
