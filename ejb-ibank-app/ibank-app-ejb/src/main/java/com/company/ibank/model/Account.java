package com.company.ibank.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "ACCOUNT", schema = "IBANK")
public class Account implements Serializable {
    private static final long serialVersionUID = 5191841255206565L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Basic(optional = false)
    @Column(name = "user_name", nullable = false, length = 40)
    private String userName;

    @Column(name = "amount")
    private double amount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Currency> currencies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Set<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Set<Currency> currencies) {
        this.currencies = currencies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (Double.compare(account.amount, amount) != 0) return false;
        if (currencies != null ? !currencies.equals(account.currencies) : account.currencies != null) return false;
        if (!id.equals(account.id)) return false;
        if (!userName.equals(account.userName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id.hashCode();
        result = 31 * result + userName.hashCode();
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (currencies != null ? currencies.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", amount=" + amount +
                ", currencies=" + currencies +
                '}';
    }
}
