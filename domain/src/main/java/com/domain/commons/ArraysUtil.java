package com.domain.commons;


import java.util.List;

public class ArraysUtil {

    private ArraysUtil(){ }

    public static boolean arraysAreEquals(String[] array1, String[] array2){
        if (array1.length != array2.length){
            return false;
        }

        for (int i = 0; i < array1.length; i++) {
            if (!array1[i].equals(array2[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean arraysAndListStringAreEquals(String[] array1, List<String> listString){
        if (array1.length != listString.size()){
            return false;
        }else{
            for(int i=0; i < array1.length; i++){
                if (!array1[i].equals(listString.get(i))){
                    return false;
                }
            }
        }
        return true;
    }

}
