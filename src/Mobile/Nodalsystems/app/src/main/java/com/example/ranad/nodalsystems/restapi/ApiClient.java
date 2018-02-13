package com.example.ranad.nodalsystems.restapi;

import android.content.Context;
import android.text.TextUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Rana D on 1/30/2018.
 */

public class ApiClient {


    public static final String API_BASE_URL = "http://cellordering.com";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null);
    }

    public static <S> S createService(
            Class<S> serviceClass, String username, String password) {
        if (!TextUtils.isEmpty(username)
                && !TextUtils.isEmpty(password)) {
            String authToken = Credentials.basic(username, password);
            return createService(serviceClass, authToken);
        }

        return createService(serviceClass, null);
    }

    public static <S> S createService(
            Class<S> serviceClass, final String authToken) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if (!httpClient.interceptors().contains(httpLoggingInterceptor)) {
            httpClient.addInterceptor(httpLoggingInterceptor);
        }

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(authToken);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);
                httpClient.readTimeout(120, TimeUnit.SECONDS);
                httpClient.connectTimeout(60, TimeUnit.SECONDS);
                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }

        return retrofit.create(serviceClass);
    }


    public static ApiInterface forgotPassword(Context context){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(120, TimeUnit.SECONDS);
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.addInterceptor(httpLoggingInterceptor);

        Retrofit.Builder builder1 = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder1
                .client(builder.build())
                .build();

        return  retrofit.create(ApiInterface.class);
    }

    public static ProductApi addProduct(Context context){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(120, TimeUnit.SECONDS);
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.addInterceptor(httpLoggingInterceptor);

        Retrofit.Builder builder1 = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder1
                .client(builder.build())
                .build();

        return  retrofit.create(ProductApi.class);
    }
}
