package com.company.ibank.services;

import com.company.ibank.AbstractTestBase;
import com.company.ibank.TestingUtils;
import com.company.ibank.dao.CurrencyDAO;
import org.jmock.auto.Mock;
import org.junit.Before;
import org.junit.Test;

public class CurrencyServiceImplTest extends AbstractTestBase {
    private CurrencyService currencyService;

    @Mock
    private CurrencyDAO currencyDAO;

    @Before
    public void setUp() throws Exception {
        currencyService = new CurrencyServiceImpl();
        TestingUtils.setPrivateField(currencyService, "accountDAO", currencyDAO);
    }

    @Test
    public void testAddCurrency() throws Exception {

    }

    @Test
    public void testRemoveCurrencies() throws Exception {

    }

    @Test
    public void testRemoveCurrency() throws Exception {

    }

    @Test
    public void testFindCurrency() throws Exception {

    }

    @Test
    public void testFindMainCurrency() throws Exception {

    }

    @Test
    public void testGetAllCurrencies() throws Exception {

    }
}