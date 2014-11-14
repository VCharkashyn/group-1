package com.company.ibank.model;

import java.util.HashMap;
import java.util.Map;

public enum AccountStatus {
    Active("active"), Close("close"), Frozen("frozen");
    private static Map<String, AccountStatus> lookup = new HashMap<String, AccountStatus>();

    private String accountStatus;

    private AccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public static AccountStatus valueOfAccountStatus(final String status) {
        for (AccountStatus accountStatus : AccountStatus.values()) {
            lookup.put(accountStatus.getAccountStatus(), accountStatus);
        }
        return lookup.get(status);
    }
}
