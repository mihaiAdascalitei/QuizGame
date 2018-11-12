package com.dasteam.quiz.quizgame.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static RetrofitService INSTANCE;
    private Retrofit retrofit;
    private static final String BASE_URL = "http://192.168.100.16:7070/";

    public static RetrofitService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RetrofitService();
        }

        return INSTANCE;
    }

    private RetrofitService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
