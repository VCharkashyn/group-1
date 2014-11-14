package com.company.ibank.dao;

import com.company.ibank.AbstractTestBase;
import com.company.ibank.exceptions.DAOException;
import com.company.ibank.model.*;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AccountDAOImplTest extends AbstractTestBase {

    @Mock
    private PersonDAO personDAOMock;
    private AccountDAO accountDAO;

    @Mock
    private Account accountMock;

    private Account account;


    @Before
    public void setup() {
        account = new Account();
        account.setOwner(new Person("Ivan", "Ivanov"));
        account.setMoney(new Money(new BigDecimal(5.0), Currency.EURO));
        account.setStatus(AccountStatus.Active);
        accountDAO = new AccountDAOImpl(personDAOMock);
    }


    @Test(expected = DAOException.class)
    public void testCreateOrUpdateAccountWithNull() throws DAOException {
        accountDAO.createOrUpdateAccount(null);
    }

    @Test
    public void testCreateOrUpdateEmptyAccount() throws DAOException {
        getContext().checking(new Expectations() {
            {
               oneOf(personDAOMock).createOrUpdatePerson(with(any(String.class)), with(any(String.class)));
            }
        });
        accountDAO.createOrUpdateAccount(account);
    }

    @Test
    public void testCreateOrUpdateAccount() throws DAOException {
        getContext().checking(new Expectations() {
            {
                oneOf(personDAOMock).createOrUpdatePerson(with(any(String.class)), with(any(String.class)));
            }
        });

        assertNull(accountDAO.createOrUpdateAccount(account));
    }

    @Test
    public void testFindById() throws DAOException {
        assertNull(accountDAO.findById(0));
    }


    @Test
    public void testGetAccounts() throws DAOException {
        assertNotNull(accountDAO.getAccounts());
    }
}
