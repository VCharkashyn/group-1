package com.company.ibank.model;


import java.io.Serializable;
import java.util.List;

public class Account implements Serializable {
    private long id;
    private String userName;
    private List<Currency> currencies;
    private double amount;

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public double getAmount() {
        return amount;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (Double.compare(account.amount, amount) != 0) return false;
        if (id != account.id) return false;
        if (currencies != null ? !currencies.equals(account.currencies) : account.currencies != null) return false;
        if (userName != null ? !userName.equals(account.userName) : account.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (currencies != null ? currencies.hashCode() : 0);
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", currencies=" + currencies +
                ", amount=" + amount +
                '}';
    }
}
