package com.example.coronareport.api;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private Retrofit retrofit;
    private OkHttpClient client;
    private ApiInterface apiInterface;
    private String  BASE_URL = "";

    public RetrofitInstance(String BASE_URL) {
        this.BASE_URL = BASE_URL;

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.readTimeout(60, TimeUnit.SECONDS);
        okHttpClient.writeTimeout(60, TimeUnit.SECONDS);
        okHttpClient.connectTimeout(60, TimeUnit.SECONDS);
        okHttpClient.addInterceptor(loggingInterceptor);
        okHttpClient.addInterceptor(chain -> {
            Request request = chain.request();

            return chain.proceed(request);
        });

        client = okHttpClient.build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        apiInterface = retrofit.create(ApiInterface.class);
    }

    public ApiInterface apiInterface() {
        return apiInterface;
    }
}
