package com.domain.commons;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ArraysUtilTest {


    @Test
    public void whenArraysAreEqualsThenReturnTrue() throws Exception{
        String[] array1 = { "hola", "adios", "pepe" };
        String[] array2 = { "hola", "adios", "pepe" };

        Assert.assertTrue(ArraysUtil.arraysAreEquals(array1, array2));
    }

    @Test
    public void whenArrayOneHasMoreItemsThenReturnFalse() throws Exception{
        String[] array1 = { "cadena1", "cadena2", "cadena3", "cadena4" };
        String[] array2 = { "cadena1", "cadena2", "cadena3" };

        Assert.assertFalse(ArraysUtil.arraysAreEquals(array1, array2));
    }

    @Test
    public void whenArrayTwoHasMoreItemsThenReturnFalse() throws Exception{
        String[] array1 = { "cadena1", "cadena2", "cadena3" };
        String[] array2 = { "cadena1", "cadena2", "cadena3", "cadena4"};

        Assert.assertFalse(ArraysUtil.arraysAreEquals(array1, array2));
    }

    @Test
    public void whenArrayOneIsEmptyThenReturnFalse() throws Exception{
        String[] array1 = { };
        String[] array2 = { "cadena1", "cadena2", "cadena3" };

        Assert.assertFalse(ArraysUtil.arraysAreEquals(array1, array2));
    }

    @Test
    public void whenArrayTwoIsEmptyThenReturnFalse() throws Exception{
        String[] array1 = { "cadena1" };
        String[] array2 = { };

        Assert.assertFalse(ArraysUtil.arraysAreEquals(array1, array2));
    }

    @Test
    public void whenBothArrayAreEmptyThenReturnTrue() throws Exception{
        String[] array1 = { };
        String[] array2 = { };

        Assert.assertTrue(ArraysUtil.arraysAreEquals(array1, array2));
    }

    @Test
    public void whenArrayOneHasSameNameItemThatArrayTwoThenReturnFalse() throws Exception{
        String[] array1 = { "cadena1", "cadena2" };
        String[] array2 = { "cadena1", "cadena1"};

        Assert.assertFalse(ArraysUtil.arraysAreEquals(array1, array2));
    }

    @Test
    public void whenArrayHasMoreItemThanListThenReturnFalse() throws Exception{
        String[] array = { "cadena1", "cadena2" };
        List<String> arrayList = Arrays.asList("cadena1");

        Assert.assertFalse(ArraysUtil.arraysAndListStringAreEquals(array, arrayList));
    }

    @Test
    public void whenListHasMoreItemThanArrayThenReturnFalse() throws Exception{
        String[] array = { "cadena1", "cadena2" };
        List<String> arrayList = Arrays.asList("cadena1" , "cadena2", "cadena3");
        Assert.assertFalse(ArraysUtil.arraysAndListStringAreEquals(array, arrayList));
    }

    @Test
    public void whenArrayAndListHasSameItemsThenReturnTrue() throws Exception{
        String[] array = { "cadena1", "cadena2" };
        List<String> arrayList = Arrays.asList("cadena1" , "cadena2");
        Assert.assertTrue(ArraysUtil.arraysAndListStringAreEquals(array, arrayList));
    }

    @Test
    public void whenArrayAndListHasSameNameItemsThenReturnFalse() throws Exception{
        String[] array = { "cadena1", "cadena1" };
        List<String> arrayList = Arrays.asList("cadena1" , "cadena2");
        Assert.assertFalse(ArraysUtil.arraysAndListStringAreEquals(array, arrayList));
    }
}