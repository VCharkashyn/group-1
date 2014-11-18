package com.company.ibank.model;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "RATE", schema = "IBANK")
public class Rate implements Serializable {
    private static final long serialVersionUID = 554825125165251266L;

    @EmbeddedId
    private RatePK id;

    @Basic(optional = false)
    @Column(name = "rate", nullable = false)
    private BigDecimal rate;

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public RatePK getId() {
        return id;
    }

    public void setId(RatePK id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rate rate1 = (Rate) o;

        if (id != null ? !id.equals(rate1.id) : rate1.id != null) return false;
        if (rate != null ? !rate.equals(rate1.rate) : rate1.rate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", rate=" + rate +
                '}';
    }
}
