package com.company.ibank.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class RatePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "primary_cur", nullable = false)
    private String primaryCurrency;

    @Basic(optional = false)
    @Column(name = "secondary_cur", nullable = false)
    private String secondaryCurrency;

    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    @Column(name = "conversion_date", nullable = false)
    private Date conversionDate;

    public String getPrimaryCurrency() {
        return primaryCurrency;
    }

    public void setPrimaryCurrency(String primaryCurrency) {
        this.primaryCurrency = primaryCurrency;
    }

    public String getSecondaryCurrency() {
        return secondaryCurrency;
    }

    public void setSecondaryCurrency(String secondaryCurrency) {
        this.secondaryCurrency = secondaryCurrency;
    }

    public Date getConversionDate() {
        return conversionDate;
    }

    public void setConversionDate(Date conversionDate) {
        this.conversionDate = conversionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RatePK ratePK = (RatePK) o;

        if (conversionDate != null ? !conversionDate.equals(ratePK.conversionDate) : ratePK.conversionDate != null)
            return false;
        if (primaryCurrency != null ? !primaryCurrency.equals(ratePK.primaryCurrency) : ratePK.primaryCurrency != null)
            return false;
        if (secondaryCurrency != null ? !secondaryCurrency.equals(ratePK.secondaryCurrency) : ratePK.secondaryCurrency != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = primaryCurrency != null ? primaryCurrency.hashCode() : 0;
        result = 31 * result + (secondaryCurrency != null ? secondaryCurrency.hashCode() : 0);
        result = 31 * result + (conversionDate != null ? conversionDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RatePK{" +
                "primaryCurrency='" + primaryCurrency + '\'' +
                ", secondaryCurrency='" + secondaryCurrency + '\'' +
                ", conversionDate=" + conversionDate +
                '}';
    }
}
