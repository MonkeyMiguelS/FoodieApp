package com.jmperezra.foodie.commons.network;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

public class HostSelectionInterceptor implements Interceptor {

    public enum SchemeHttp {
        HTTP("http"), HTPPS("https");

        private String value;

        public String getValue(){
            return this.value;
        }

        SchemeHttp(String value) {
            this.value = value;
        }
    }

    private String host;
    private SchemeHttp scheme = null;
    private int port = 80;

    public HostSelectionInterceptor() { }

    public void setScheme(SchemeHttp scheme){
        this.scheme = scheme;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (host != null) {
            HttpUrl newUrl = request
                    .url()
                    .newBuilder()
                    .scheme((scheme == null) ? SchemeHttp.HTTP.getValue() : scheme.getValue())
                    .port(port)
                    .host(host)
                    .build();

            request = request.newBuilder()
                    .url(newUrl)
                    .build();
        }
        return chain.proceed(request);
    }
}