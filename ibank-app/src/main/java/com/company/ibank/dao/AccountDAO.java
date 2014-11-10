package com.company.ibank.dao;


import com.company.ibank.exceptions.DAOException;
import com.company.ibank.model.Account;

import java.util.List;

public interface AccountDAO {

    Long createOrUpdateAccount(Account account) throws DAOException;

    Account findById(long id) throws DAOException;

    List<Account> getAccounts() throws DAOException;
}
