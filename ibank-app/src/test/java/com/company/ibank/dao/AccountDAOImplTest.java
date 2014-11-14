package com.company.ibank.dao;

import com.company.ibank.AbstractTestBase;
import com.company.ibank.exceptions.DAOException;
import org.jmock.auto.Mock;
import org.junit.Before;
import org.junit.Test;

public class AccountDAOImplTest extends AbstractTestBase {

    @Mock
    private PersonDAO personDAOMock;
    private AccountDAO accountDAO;

    @Before
    public void setup() {
        accountDAO = new AccountDAOImpl(personDAOMock);
    }


    @Test(expected = DAOException.class)
    public void testCreateOrUpdateAccountWithNull() throws DAOException {
        accountDAO.createOrUpdateAccount(null);
    }

    @Test
    public void testCreateOrUpdateAccount() throws DAOException {

    }

    @Test
    public void testFindById() {

    }

    @Test
    public void testGetAccounts() {

    }
}
