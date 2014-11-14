package com.company.ibank.services.comparators;

import com.company.ibank.model.Account;
import com.company.ibank.model.Money;
import java.util.Comparator;

public class AccountSortedByCurrency implements Comparator<Account> {
    @Override
    public int compare(Account o1, Account o2) {
        Money accountMoney1 = o1.getMoney();
        Money accountMoney2 = o2.getMoney();

        return accountMoney1.getCurrency().compareTo(accountMoney2.getCurrency());
    }
}
