package com.domain.commons;

import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

public class NumberCurrencyUtilTest {

    @Test
    public void checkDecimalWithLocaleSys() throws Exception{
        double num = 100.02;
        String value = NumberCurrencyUtil.formatNumber(num);
        Assert.assertEquals(value, "100,02");
    }

    @Test
    public void checkDecimalWithZeroAndLocaleSys() throws Exception{
        double num = 100.00;
        String value = NumberCurrencyUtil.formatNumber(num);
        Assert.assertEquals(value, "100");
    }

    @Test
    public void checkLargeDecimalWithLocaleSys() throws Exception{
        double num = 100.02123;
        String value = NumberCurrencyUtil.formatNumber(num);
        Assert.assertEquals(value, "100,02");
    }

    @Test
    public void checkLargeNumberWithDecimalAndLocaleSys() throws Exception{
        double num = 100000000.02123;
        String value = NumberCurrencyUtil.formatNumber(num);
        Assert.assertEquals(value, "100.000.000,02");
    }

    @Test
    public void checkLargeNumberWithLocaleSys() throws Exception{
        double num = 100000000;
        String value = NumberCurrencyUtil.formatNumber(num);
        Assert.assertEquals(value, "100.000.000");
    }

    @Test
    public void checkDecimalWithLocaleEn() throws Exception{
        double num = 100.02;
        String value = NumberCurrencyUtil.formatNumber(num, Locale.ENGLISH);
        Assert.assertEquals(value, "100.02");
    }

    @Test
    public void checkLargeDecimalWithLocaleEn() throws Exception{
        double num = 100.02123;
        String value = NumberCurrencyUtil.formatNumber(num, Locale.ENGLISH);
        Assert.assertEquals(value, "100.02");
    }

    @Test
    public void checkLargeNumberWithDecimalAndLocaleEn() throws Exception{
        double num = 100000000.02123;
        String value = NumberCurrencyUtil.formatNumber(num, Locale.ENGLISH);
        Assert.assertEquals(value, "100,000,000.02");
    }

    @Test
    public void checkLargeNumberWithLocaleEn() throws Exception{
        double num = 100000000;
        String value = NumberCurrencyUtil.formatNumber(num, Locale.ENGLISH);
        Assert.assertEquals(value, "100,000,000");
    }

    @Test
    public void checkLargeNumberWithDecimalAndLocaleGe() throws Exception{
        double num = 100000000.02123;
        String value = NumberCurrencyUtil.formatNumber(num, Locale.GERMAN);
        Assert.assertEquals(value, "100.000.000,02");
    }
}