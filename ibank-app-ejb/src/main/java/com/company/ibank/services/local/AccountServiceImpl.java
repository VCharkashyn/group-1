package com.company.ibank.services.local;


import com.company.ibank.dao.AccountDAO;
import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.Account;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class AccountServiceImpl implements AccountService{

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
}
