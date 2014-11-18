package com.company.ibank.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CURRENCY", schema = "IBANK")
public class Currency implements Serializable {
    private static final long serialVersionUID = 5548825985265251266L;

    @Id
    @Basic(optional = false)
    @Column(name = "currency", nullable = false, unique = true)
    private String currency;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Currency currency1 = (Currency) o;

        if (currency != null ? !currency.equals(currency1.currency) : currency1.currency != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return currency != null ? currency.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currency='" + currency + '\'' +
                '}';
    }
}
