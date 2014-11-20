package com.company.ibank.services.local;

import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.Account;
import com.company.ibank.model.Currency;

import javax.ejb.Local;
import java.util.List;

@Local
public interface AccountService {
    Long openAccount(Account account);

    void closeAccount(Account account) throws ServiceException;

    void closeAccounts(Long[] ids) throws ServiceException;

    Account findAccount(long id);

    List<Account> getAllAccounts();

    boolean isCurrenciesExistInAccount(List<Currency> currencies);
}
