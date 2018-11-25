package com.dasteam.quiz.quizgame.network.call;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PremiumCall {
    @GET("/premium/{id}")
    public Call<String> makePremium(@Path("id") String id);
}
