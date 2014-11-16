package com.company.ibank.services;

import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.Account;
import java.util.List;

public interface AccountService {

    Long close(Long person) throws ServiceException;

    void freezeAccount(Long id) throws ServiceException;

    void unFreezeAccount(Long id) throws ServiceException;

    Account find(Long accountId) throws ServiceException;

    List<Account> getAllAccounts() throws ServiceException;
}
