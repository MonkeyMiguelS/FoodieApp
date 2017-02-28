package com.data.testcommons.webserver;

import java.util.Map;
import java.util.TreeMap;

import okhttp3.Headers;
import okhttp3.mockwebserver.MockResponse;

public class MockApiResponse {

    public static final String MOCK_DEFAULT_TOKEN = "0123456789";

    private static Headers getHeaders(String token){
        Map<String, String> mapHeader = new TreeMap<>();
        mapHeader.put("Content-Type", "application/json; charset=utf-8");
        mapHeader.put("Cache-Control", "no-cache");
        return Headers.of(mapHeader);
    }

    /**
     * Servicio para realizar el login.
     *
     * @return
     */
    public static final MockResponse serviceLoginSuccess(){
        return serviceLoginSuccess(MOCK_DEFAULT_TOKEN);
    }

    public static final MockResponse serviceLoginSuccess(String token){
        return new MockResponse()
                .setHeaders(getHeaders(token))
                .setBody(ResponseStubs.getResponseLoginOk())
                .setResponseCode(200);
    }

    public static final MockResponse serviceLoginAliasError(){
        return new MockResponse()
                .setHeaders(getHeaders(""))
                .setBody(ResponseStubs.getResponseLoginWithAliasError())
                .setResponseCode(400);
    }

    public static final MockResponse serviceLoginPassError(){
        return new MockResponse()
                .setHeaders(getHeaders(""))
                .setBody(ResponseStubs.getResponseLoginWithPasswordError())
                .setResponseCode(400);
    }
}
