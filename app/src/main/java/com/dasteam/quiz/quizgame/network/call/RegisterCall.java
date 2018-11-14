package com.dasteam.quiz.quizgame.network.call;

import com.dasteam.quiz.quizgame.model.PlayerModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterCall {
    @POST("/register")
    public Call<PlayerModel> register(@Body PlayerModel player);
}
