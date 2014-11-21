package com.company.ibank.services.remote;

import com.company.ibank.services.CurrencyService;

import javax.ejb.Remote;

@Remote
public interface CurrencyServiceRemote  extends CurrencyService {
}
