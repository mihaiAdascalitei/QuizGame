package com.dasteam.quiz.quizgame.network.call;

import com.dasteam.quiz.quizgame.model.player.PlayerModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PremiumCall {
    @GET("/premium/{id}")
    public Call<PlayerModel> makePremium(@Path("id") String id);
}
