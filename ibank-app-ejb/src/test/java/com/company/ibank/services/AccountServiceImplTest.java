package com.company.ibank.services;

import com.company.ibank.AbstractTestBase;
import com.company.ibank.TestingUtils;
import com.company.ibank.dao.AccountDAO;
import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.Account;
import com.company.ibank.model.Currency;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junitx.framework.Assert.assertNull;

public class AccountServiceImplTest extends AbstractTestBase {
    private static final long FAKE_ID = 46L;
    private AccountService accountService;

    @Mock
    private AccountDAO accountDAO;

    private Account account;
    private Currency currency;

    @Before
    public void setUp() throws Exception {
        accountService = new AccountServiceImpl();
        currency = new Currency();
        account = new Account();
        account.setCurrencies(new HashSet<Currency>(Arrays.asList(currency)));
        account.setId(FAKE_ID);
        TestingUtils.setPrivateField(accountService, "accountDAO", accountDAO);
    }

    @Test
    public void testOpenAccountWithNull() {
        assertNull(accountService.openAccount(null));
    }

    @Test
    public void testOpenAccount() {
        getContext().checking(new Expectations() {
            {
                oneOf(accountDAO).create(with(any(Account.class)));
                will(returnValue(FAKE_ID));
            }
        });

        assertNotNull(accountService.openAccount(account));
    }

    @Test
    public void testCloseAccount() throws Exception {
        getContext().checking(new Expectations() {
            {
                oneOf(accountDAO).remove(account);
            }
        });

        accountService.closeAccount(account);
    }

    @Test(expected = ServiceException.class)
    public void testCloseAccountWithNull() throws Exception {
        accountService.closeAccount(null);
    }

    @Test(expected = ServiceException.class)
    public void testCloseAccountsWithNull() throws Exception {
        accountService.closeAccounts(null);
    }

    @Test
    public void testCloseAccounts() throws Exception {
        final Long[] ID = {FAKE_ID};

        getContext().checking(new Expectations() {
            {
                oneOf(accountDAO).removeByIDs(ID);
            }
        });

        accountService.closeAccounts(ID);
    }


    @Test
    public void testFindAccount() throws Exception {
        getContext().checking(new Expectations() {
            {
                oneOf(accountDAO).findById(FAKE_ID);
                will(returnValue(account));
            }
        });

        assertEquals(account, accountService.findAccount(FAKE_ID));
    }

    @Test
    public void testFindAccountWithNullResp() throws Exception {
        getContext().checking(new Expectations() {
            {
                oneOf(accountDAO).findById(FAKE_ID);
                will(returnValue(null));
            }
        });

        assertNull(accountService.findAccount(FAKE_ID));
    }

    @Test
    public void testGetAllAccounts() throws Exception {
        getContext().checking(new Expectations() {
            {
                oneOf(accountDAO).getAccounts();
                will(returnValue(Arrays.asList(account)));
            }
        });

        assertEquals(1, accountService.getAllAccounts().size());
    }

    @Test
    public void testIsCurrenciesExistInAccount() throws Exception {
        getContext().checking(new Expectations() {
            {
                oneOf(accountDAO).isContainingCurrencies(Arrays.asList(currency), FAKE_ID);
                will(returnValue(true));
            }
        });

        assertTrue(accountService.isCurrenciesExistInAccount(Arrays.asList(currency), FAKE_ID));
    }
}