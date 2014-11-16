package com.company.ibank.views;

import com.company.ibank.exceptions.StringConversionException;

import java.math.BigDecimal;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ViewUtils {

    public static BigDecimal stringToBigDecimal(final String formattedString,
            final Locale locale) throws StringConversionException {
        final DecimalFormatSymbols symbols;
        final char groupSeparatorChar;
        final String groupSeparator;
        final char decimalSeparatorChar;
        final String decimalSeparator;
        String fixedString;
        final BigDecimal number;

        symbols = new DecimalFormatSymbols(locale);
        groupSeparatorChar = symbols.getGroupingSeparator();
        decimalSeparatorChar = symbols.getDecimalSeparator();

        if (groupSeparatorChar == '.') {
            groupSeparator = "\\" + groupSeparatorChar;
        } else {
            groupSeparator = Character.toString(groupSeparatorChar);
        }

        if (decimalSeparatorChar == '.') {
            decimalSeparator = "\\" + decimalSeparatorChar;
        } else {
            decimalSeparator = Character.toString(decimalSeparatorChar);
        }

        try {
            fixedString = formattedString.replaceAll(groupSeparator, "");
            fixedString = fixedString.replaceAll(decimalSeparator, ".");
            number = new BigDecimal(fixedString);
            return ( number );
        }
        catch (NumberFormatException ex) {
            throw new StringConversionException("Number is right format" ,ex);
        }
    }

    public static Long stringToLong(String number) throws StringConversionException {
        try {
            return new Long(number);
        }
        catch (NumberFormatException ex) {
            throw new StringConversionException("String to long exception", ex);
        }
    }

    public static Integer stringToInt(String number) throws StringConversionException {
        try {
            return new Integer(number);
        }
        catch (NumberFormatException ex) {
            throw new StringConversionException("String to long exception", ex);
        }
    }

    public static Date stringToDate(String month, String year) throws StringConversionException {
        try {
            String template = month + "." + year;
            SimpleDateFormat format = new SimpleDateFormat("MM.yyyy");
            return (Date) format.parse(template);
        }
        catch (ParseException ex) {
            throw new StringConversionException("string to date error", ex);
        }

    }
}
