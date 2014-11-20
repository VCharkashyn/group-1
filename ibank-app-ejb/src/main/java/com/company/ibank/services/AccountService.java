package com.company.ibank.services;

import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.Account;
import com.company.ibank.model.Currency;

import javax.annotation.security.RolesAllowed;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

public interface AccountService {

    @RolesAllowed("ibank")
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    Long openAccount(Account account);

    @RolesAllowed("ibank")
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    void closeAccount(Account account) throws ServiceException;

    @RolesAllowed("ibank")
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    void closeAccounts(Long[] ids) throws ServiceException;

    @RolesAllowed("ibank")
    Account findAccount(long id);

    @RolesAllowed("ibank")
    List<Account> getAllAccounts();

    @RolesAllowed("ibank")
    boolean isCurrenciesExistInAccount(final List<Currency> currencies, final Long id);
}
