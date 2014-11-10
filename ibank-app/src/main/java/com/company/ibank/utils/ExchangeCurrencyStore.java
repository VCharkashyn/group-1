package com.company.ibank.utils;

import java.math.BigDecimal;

public class ExchangeCurrencyStore {
    public static final String EURO = "euro";
    public static final String USD = "usd";
    public static final String BYR = "byr";

    public static final BigDecimal USD2EUR = new BigDecimal(0.8);
    public static final BigDecimal USD2BYR = new BigDecimal(10735);

    public static final BigDecimal EUR2USD = new BigDecimal(1.2514);
    public static final BigDecimal EUR2BYR = new BigDecimal(13430);

    public static final BigDecimal BYR2EUR = new BigDecimal(13430);
    public static final BigDecimal BYR2USD = new BigDecimal(10765);
}
