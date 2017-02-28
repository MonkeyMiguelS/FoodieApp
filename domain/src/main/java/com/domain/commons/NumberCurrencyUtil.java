package com.domain.commons;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberCurrencyUtil {

    /**
     * Devuelve un double en un formato de moneda con el LOCALE del dispositivo.
     *
     * @param number
     * @return
     */
    public static String formatNumber(double number){
        return formatNumber(number, Locale.getDefault());
    }

    /**
     * Devuelve el double en un formato de moneda según el LOCALE pasado.
     * @param number
     * @param fmtLocale
     * @return
     */
    public static String formatNumber(double number, Locale fmtLocale){
        NumberFormat formatter = NumberFormat.getInstance(fmtLocale);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(0);
        return formatter.format(number);
    }

    public static double inverseFormatNumber(String number) throws Exception{
        return inverseFormatNumber(number, Locale.getDefault());
    }

    public static double inverseFormatNumber(String number, Locale fmtLocale) throws Exception {
        NumberFormat formatter = NumberFormat.getInstance(fmtLocale);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(0);
        return formatter.parse(number).doubleValue();
    }
}
