package com.company.ibank.dao;


import com.company.ibank.model.Account;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class AccountDAOImpl implements AccountDAO {
    private static final Map<Long, Account> storage = new ConcurrentHashMap<Long, Account>();

    @Override
    public Long create(Account account) {
        if (account != null) {
            long id = account.getId();
            if (storage.containsKey(id)) {
                storage.put(id, account);
            } else {
                storage.put(Collections.max(storage.keySet())+1, account);
            }
        }

        return null;
    }

    @Override
    public void remove(long id) {
        storage.remove(id);
    }

    @Override
    public Account findById(long id) {
        return storage.get(id);
    }

    @Override
    public List<Account> getAccounts() {
        return new ArrayList<Account>(storage.values());
    }
}
