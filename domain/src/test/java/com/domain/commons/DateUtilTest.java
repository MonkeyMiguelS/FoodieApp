package com.domain.commons;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class DateUtilTest {

    private final String formatES = "dd-MM-yyyy";
    private final String formatUk = "MM-dd-yyyy";

    private final SimpleDateFormat formatterES = new SimpleDateFormat(formatES);
    private final SimpleDateFormat formatterUk = new SimpleDateFormat(formatUk);

    @Test
    public void whenFormatIsESThenReturnStringWithFormatES() throws Exception {
        //Given
        String dateEs = "01-10-2017";
        Date dateRes = formatterES.parse(dateEs);

        //When
        String strDateRes = DateUtil.mapDateToString(dateRes, formatES);

        //Then
        Assert.assertEquals(dateEs, strDateRes);
    }

    @Test
    public void whenFormatIsUkThenReturnStringWithFormatUk() throws Exception {
        //Given
        String dateUk = "01-21-2017";
        Date dateRes = formatterUk.parse(dateUk);

        //When
        String strDateRes = DateUtil.mapDateToString(dateRes, formatUk);

        //Then
        Assert.assertEquals(dateUk, strDateRes);
    }

    @Test
    public void whenHasFormatESThenCurrentDateWithFormatES() throws Exception{
        //Given
        Date currentDate = new Date();
        String nowStr = DateUtil.mapDateToString(currentDate, formatES);

        //When
        String currentStrDate = DateUtil.currentDateToString(formatES);

        //Then
        Assert.assertEquals(nowStr, currentStrDate);
    }

    @Test
    public void whenHasFormatUKThenCurrentDateWithFormatUK() throws Exception{
        //Given
        Date currentDate = new Date();
        String nowStr = DateUtil.mapDateToString(currentDate, formatUk);

        //When
        String currentStrDate = DateUtil.currentDateToString(formatUk);

        //Then
        Assert.assertEquals(nowStr, currentStrDate);
    }

    @Test
    public void whenDateStringEsIsCorrectThenReturnDate() throws Exception{
        //Given
        String strDateEs = "01-10-2017";
        BigInteger msDateEs = new BigInteger("1506808800000");

        //When
        Date dateRes = DateUtil.mapStringToDate(strDateEs, formatES);

        //Then
        Assert.assertEquals(dateRes.getTime(), msDateEs.longValue());
    }

    @Test(expected = ParseException.class)
    public void whenDateStringEsIsNotCorrectThenThrowException() throws Exception{
        //Given
        String strDateEs = "15-Feb-2017";
        BigInteger msDateEs = new BigInteger("1506808800000");

        //When
        Date dateRes = DateUtil.mapStringToDate(strDateEs, formatES);

        //Then
        Assert.assertEquals(dateRes.getTime(), msDateEs.longValue());
    }
}