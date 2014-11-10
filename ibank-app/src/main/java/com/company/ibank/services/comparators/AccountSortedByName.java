package com.company.ibank.services.comparators;

import com.company.ibank.model.Account;
import com.company.ibank.model.Person;

import java.util.Comparator;

public class AccountSortedByName implements Comparator<Account> {
    @Override
    public int compare(Account o1, Account o2) {
        Person accountPerson1 = o1.getOwner();
        Person accountPerson2 = o2.getOwner();

        return accountPerson1.getName().compareTo(accountPerson2.getName());
    }
}
