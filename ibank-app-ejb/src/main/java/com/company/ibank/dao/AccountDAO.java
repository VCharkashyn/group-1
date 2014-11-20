package com.company.ibank.dao;


import com.company.ibank.model.Account;

import javax.ejb.Local;
import java.util.List;

@Local
public interface AccountDAO {
    Long create(Account account);

    void remove(Account account);

    int removeByIDs(Long[] ids);

    Account findById(long id);

    List<Account> getAccounts();
}
