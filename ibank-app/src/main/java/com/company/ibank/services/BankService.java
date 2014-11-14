package com.company.ibank.services;


import com.company.ibank.exceptions.ServiceException;
import com.company.ibank.model.Account;
import com.company.ibank.model.Currency;
import com.company.ibank.model.Money;
import java.util.List;

public interface BankService {
    void exchange(Long id, Currency currency) throws ServiceException;

    List<Account> filterByName(String s, String s1) throws ServiceException;

    List<Account> filterByCurrency(Currency currency) throws ServiceException;

    List<Account> sortByName() throws ServiceException;

    List<Account> sortByCurrency() throws ServiceException;
}
