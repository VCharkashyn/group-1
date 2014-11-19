package com.company.ibank.dao;


import com.company.ibank.exceptions.DAOException;
import com.company.ibank.model.Account;

import javax.ejb.Local;
import javax.ejb.Stateful;
import java.util.List;

@Local
public interface AccountDAO {
    Long create(Account account) throws DAOException;

    void remove(Account account) throws DAOException;

    Account findById(long id) throws DAOException;

    List<Account> getAccounts() throws DAOException;
}
