package com.company.ibank.services;

import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.Account;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Local
@Stateless
public interface AccountService {
    Long openAccount(Account account) throws ServiceException;

    void closeAccount(long id) throws ServiceException;

    void closeAccount(Account account) throws ServiceException;

    Account findAccount(long id) throws ServiceException;

    List<Account> getAllAccounts() throws ServiceException;
}
