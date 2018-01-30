package com.example.ranad.nodalsystems.restapi;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Rana D on 1/30/2018.
 */

public class AuthenticationInterceptor implements Interceptor{

    private String authToken;

    public AuthenticationInterceptor(String token) {
        this.authToken = token;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Log.d("authToken", authToken);
        Request.Builder builder = original.newBuilder()
                .header("Authorization", authToken);

        Request request = builder.build();
        return chain.proceed(request);
    }
}
