package com.data.testcommons.webserver;

import java.io.InputStream;

public class ResponseStubs {

    private ResponseStubs(){ }

    private static String getResponseJson(String nameFile){
        try {
            //return convertToString(ResponseStubs.class.getClassLoader().getResourceAsStream( "raw/" + nameFile));
            return TestResourceHelper.readFile(nameFile);
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }

    private static String convertToString(InputStream inputStream){
        java.util.Scanner s = new java.util.Scanner(inputStream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public static String getResponseLoginOk(){
        return getResponseJson("responseLogin_200.json");
    }

    public static String getResponseLoginWithAliasError(){
        return getResponseJson("responseLogin_400_AliasError.json");
    }

    public static String getResponseLoginWithPasswordError(){
        return getResponseJson("responseLogin_400_PassError.json");
    }
}
