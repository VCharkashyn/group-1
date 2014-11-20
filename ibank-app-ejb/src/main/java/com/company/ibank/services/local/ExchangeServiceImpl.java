package com.company.ibank.services.local;


import com.company.ibank.exceptions.CurrencyNotFound;
import com.company.ibank.exceptions.RateNotFound;
import com.company.ibank.exceptions.UnsupportedChangeCurrrencyRequest;
import com.company.ibank.model.Account;
import com.company.ibank.model.Currency;
import com.company.ibank.model.Rate;
import com.company.ibank.utils.configurators.ConfigurationManager;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import java.math.BigDecimal;
import java.util.Arrays;

public class ExchangeServiceImpl implements ExchangeRateService {
    private static final String MAIN_CURRENCY_KEY = "MAIN_CURRENCY";//usd

    @EJB
    private RateService rateService;

    @EJB
    private CurrencyService currencyService;

    @EJB
    private AccountService accountService;

//In Progress
    @Override
    public BigDecimal exchangeCurrencies(Account account, String primaryCurrency, String secondaryCurrency) {

        if (account == null || StringUtils.isBlank(primaryCurrency) || StringUtils.isEmpty(secondaryCurrency)) {
            return null;
        }

        //check existing currencies in account
        Currency primCurrency = currencyService.findCurrency(primaryCurrency);
        Currency secondCurrency = currencyService.findCurrency(secondaryCurrency);

        if (primCurrency == null || secondCurrency == null) {
            throw new CurrencyNotFound("");
        }

        boolean isCurrenciesExist = accountService.isCurrenciesExistInAccount(Arrays.asList(new Currency[]{primCurrency, secondCurrency}));

        if (!isCurrenciesExist) {
            throw new UnsupportedChangeCurrrencyRequest("");
        }

        final Rate rate = rateService.findRate(primCurrency, secondCurrency);

        if (rate == null) {
            throw new RateNotFound();
        }

        final BigDecimal acccountAmount = new BigDecimal(account.getAmount());

        if (StringUtils.equalsIgnoreCase(primaryCurrency, ConfigurationManager.getInstance().getProperty(MAIN_CURRENCY_KEY))) {
            return acccountAmount.multiply(rate.getRate());
        } else {
            Rate rateFromBaseCurrency = rateService.findRate(ConfigurationManager.getInstance().
                    getProperty(MAIN_CURRENCY_KEY), primaryCurrency);

            if (rateFromBaseCurrency == null) {
                throw new RateNotFound();
            }

            return rateFromBaseCurrency.getRate().multiply(rate.getRate()).multiply(acccountAmount);
        }
    }
}
