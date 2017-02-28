package com.mo2o.ruralvia.webserver;

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
        mapHeader.put("CODSecIp", token);
        mapHeader.put("CODTerminal","");
        mapHeader.put("CODSecUser","03030100");
        mapHeader.put("CODSecEnt","");
        mapHeader.put("CODApl","BDP");
        mapHeader.put("CODSecTrans","");

        return Headers.of(mapHeader);
    }

    /**
     * Servicio para realizar el login.
     *
     * @return
     */
    public static final MockResponse serviceSE_RVA_LoginBE (){
        return serviceSE_RVA_LoginBE(MOCK_DEFAULT_TOKEN);
    }

    public static final MockResponse serviceSE_RVA_LoginBE (String token){
        return new MockResponse()
                .setHeaders(getHeaders(token))
                .setBody(ResponseStubs.getResponseLoginOk())
                .setResponseCode(200);
    }

    public static final MockResponse serviceKoSE_RVA_LoginBEAliasError (){
        return new MockResponse()
                .setHeaders(getHeaders(""))
                .setBody(ResponseStubs.getResponseLoginWithAliasError())
                .setResponseCode(400);
    }

    public static final MockResponse serviceKoSE_RVA_LoginBEPassError (){
        return new MockResponse()
                .setHeaders(getHeaders(""))
                .setBody(ResponseStubs.getResponseLoginWithPasswordError())
                .setResponseCode(400);
    }

    public static final MockResponse serviceOKSE_CI_ConsultaDatosComerciosTpvs(){
        return new MockResponse()
                .setHeaders(getHeaders(""))
                .setBody(ResponseStubs.getResponseSelectorEstablishmentsOk())
                .setResponseCode(200);
    }
}
