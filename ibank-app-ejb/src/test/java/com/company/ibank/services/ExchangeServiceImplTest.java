package com.company.ibank.services;

import com.company.ibank.AbstractTestBase;
import com.company.ibank.TestingUtils;
import org.jmock.auto.Mock;
import org.junit.Before;
import org.junit.Test;

public class ExchangeServiceImplTest extends AbstractTestBase {

    private ExchangeRateService exchangeService;

    @Mock
    private RateService rateService;

    @Mock
    private CurrencyService currencyService;

    @Mock
    private AccountService accountService;

    @Before
    public void setUp() throws Exception {
        exchangeService = new ExchangeServiceImpl();
        TestingUtils.setPrivateField(exchangeService, "accountService", accountService);
        TestingUtils.setPrivateField(exchangeService, "currencyService", currencyService);
        TestingUtils.setPrivateField(exchangeService, "rateService", rateService);
    }

    @Test
    public void testExchangeCurrencies() throws Exception {

    }
}