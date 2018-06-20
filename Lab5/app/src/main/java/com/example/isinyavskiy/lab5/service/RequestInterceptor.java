package com.example.isinyavskiy.lab5.service;

import com.example.isinyavskiy.lab5.utils.CredentialsUtils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        HttpUrl originalHttpUrl = originalRequest.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("ts", "1")
                .addQueryParameter("apikey", "a8ccbba51a802b0fdfc7e906133de17f")
                .addQueryParameter("hash", CredentialsUtils.getHash("1",
                        "c3e0ad748b2471a190cfa31ff0cfe064379378eb",
                        "a8ccbba51a802b0fdfc7e906133de17f"))
                .build();

        Request request = originalRequest.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}

