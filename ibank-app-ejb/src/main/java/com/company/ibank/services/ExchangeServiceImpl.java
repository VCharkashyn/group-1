package com.company.ibank.services;


import com.company.ibank.exceptions.CurrencyNotFound;
import com.company.ibank.exceptions.RateNotFound;
import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.exceptions.UnsupportedChangeCurrrencyRequest;
import com.company.ibank.model.Account;
import com.company.ibank.model.Currency;
import com.company.ibank.model.Rate;
import com.company.ibank.services.local.ExchangeRateServiceLocal;
import com.company.ibank.services.remote.ExchangeRateServiceRemote;
import com.company.ibank.utils.configurators.ConfigurationManager;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.security.DeclareRoles;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import java.math.BigDecimal;
import java.util.Arrays;

@Stateless
@DeclareRoles("ibank")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ExchangeServiceImpl implements ExchangeRateServiceLocal, ExchangeRateServiceRemote {
    private static final String MAIN_CURRENCY_KEY = "MAIN_CURRENCY";//usd

    @EJB
    private RateService rateService;

    @EJB
    private CurrencyService currencyService;

    @EJB
    private AccountService accountService;

    @Override
    public BigDecimal exchangeCurrencies(final Account account, final String primaryCurrency, final String secondaryCurrency) throws ServiceException {

        if (account == null || StringUtils.isBlank(primaryCurrency) || StringUtils.isEmpty(secondaryCurrency)) {
            throw new ServiceException("Unsupported parameters");
        }

        final Currency primCurrency = currencyService.findCurrency(primaryCurrency);
        final Currency secondCurrency = currencyService.findCurrency(secondaryCurrency);
        final Currency mainCurrency = currencyService.findMainCurrency();

        if (primCurrency == null || secondCurrency == null) {
            throw new CurrencyNotFound("Currencies doesn't exist in DB");
        }

        final boolean isCurrenciesExist = accountService.isCurrenciesExistInAccount(Arrays.asList(new Currency[]{primCurrency, secondCurrency}), account.getId());

        if (!isCurrenciesExist) {
            throw new UnsupportedChangeCurrrencyRequest("Client didn't support this currencies");
        }

        final Rate rate = rateService.findRate(primCurrency, secondCurrency);
        if (rate == null) {
            throw new RateNotFound("Rate doesn't exist for " + primaryCurrency + "to " + secondaryCurrency);
        }

        BigDecimal acccountAmount = new BigDecimal(account.getAmount());

        if (StringUtils.equalsIgnoreCase(primaryCurrency, ConfigurationManager.getInstance().getProperty(MAIN_CURRENCY_KEY))) {
            acccountAmount = acccountAmount.multiply(rate.getRate());
        } else {
            Rate rateFromBaseCurrency = rateService.findRate(mainCurrency, primCurrency);
            if (rateFromBaseCurrency == null) {
                throw new RateNotFound("Cross rate doesn't exist");
            }
            acccountAmount = rateFromBaseCurrency.getRate().multiply(rate.getRate()).multiply(acccountAmount);
        }

        return acccountAmount;
    }
}
