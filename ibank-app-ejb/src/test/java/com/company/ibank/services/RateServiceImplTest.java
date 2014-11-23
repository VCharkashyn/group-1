package com.company.ibank.services;

import com.company.ibank.AbstractTestBase;
import com.company.ibank.TestingUtils;
import com.company.ibank.dao.RateDAO;
import org.jmock.auto.Mock;
import org.junit.Before;
import org.junit.Test;

public class RateServiceImplTest extends AbstractTestBase {
    private RateService rateService;

    @Mock
    private RateDAO rateDAO;

    @Before
    public void setUp() throws Exception {
        rateService = new RateServiceImpl();
        TestingUtils.setPrivateField(rateService, "accountDAO", rateDAO);
    }

    @Test
    public void testAddRate() throws Exception {

    }

    @Test
    public void testRemoveRate() throws Exception {

    }

    @Test
    public void testFindRate() throws Exception {

    }

    @Test
    public void testGetRates() throws Exception {

    }

    @Test
    public void testFindRate1() throws Exception {

    }

    @Test
    public void testFindRate2() throws Exception {

    }
}