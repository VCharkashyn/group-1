package com.company.ibank.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Rate implements Serializable {

    private BigDecimal rate;

    private String primaryCurrency;

    private String secondaryCurrency;

    private Date conversionDate;


    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

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

        Rate rate1 = (Rate) o;

        if (conversionDate != null ? !conversionDate.equals(rate1.conversionDate) : rate1.conversionDate != null)
            return false;
        if (primaryCurrency != null ? !primaryCurrency.equals(rate1.primaryCurrency) : rate1.primaryCurrency != null)
            return false;
        if (rate != null ? !rate.equals(rate1.rate) : rate1.rate != null) return false;
        if (secondaryCurrency != null ? !secondaryCurrency.equals(rate1.secondaryCurrency) : rate1.secondaryCurrency != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rate != null ? rate.hashCode() : 0;
        result = 31 * result + (primaryCurrency != null ? primaryCurrency.hashCode() : 0);
        result = 31 * result + (secondaryCurrency != null ? secondaryCurrency.hashCode() : 0);
        result = 31 * result + (conversionDate != null ? conversionDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "rate=" + rate +
                ", primaryCurrency='" + primaryCurrency + '\'' +
                ", secondaryCurrency='" + secondaryCurrency + '\'' +
                ", conversionDate=" + conversionDate +
                '}';
    }
}
