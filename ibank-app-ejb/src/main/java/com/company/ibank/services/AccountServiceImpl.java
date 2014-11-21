package com.company.ibank.services;


import com.company.ibank.dao.AccountDAO;
import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.Account;
import com.company.ibank.model.Currency;
import com.company.ibank.services.local.AccountServiceLocal;
import com.company.ibank.services.remote.AccountServiceRemote;

import javax.annotation.security.DeclareRoles;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import java.util.List;

@Stateless
@DeclareRoles("ibank")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AccountServiceImpl implements AccountServiceLocal, AccountServiceRemote {

    @EJB
    private AccountDAO accountDAO;

    @Override
    public Long openAccount(final Account account) {
        if (account == null) {
            return null;
        }

        return accountDAO.create(account);
    }

    @Override
     public void closeAccount(final Account account) throws ServiceException {
        if (account == null) {
            throw new ServiceException("Unsupported parameter:account");
        }

        accountDAO.remove(account);
    }

    @Override
    public void closeAccounts(final Long[] ids) throws ServiceException {
        if (ids == null) {
            throw new ServiceException("Unsupported parameter:account ids");
        }

        accountDAO.removeByIDs(ids);
    }

    @Override
    public Account findAccount(final long id) {
        return accountDAO.findById(id);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountDAO.getAccounts();
    }

    @Override
    public boolean isCurrenciesExistInAccount(final List<Currency> currencies, final Long id) {
        return accountDAO.isContainingCurrencies(currencies, id);
    }
}
