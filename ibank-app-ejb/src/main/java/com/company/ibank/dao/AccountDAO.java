package com.company.ibank.dao;


import com.company.ibank.model.Account;
import com.company.ibank.model.Currency;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

@Local
public interface AccountDAO {
    @RolesAllowed("ibank")
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    Long create(Account account);

    @RolesAllowed("ibank")
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    void remove(Account account);

    @RolesAllowed("ibank")
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    int removeByIDs(Long[] ids);

    @RolesAllowed("ibank")
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    Account findById(long id);

    @RolesAllowed("ibank")
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    List<Account> getAccounts();

    @RolesAllowed("ibank")
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    boolean isContainingCurrencies(List<Currency> currencies, Long id);
}
