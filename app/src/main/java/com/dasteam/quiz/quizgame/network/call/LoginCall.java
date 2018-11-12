package com.dasteam.quiz.quizgame.network.call;

import com.dasteam.quiz.quizgame.model.PlayerModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginCall {
    @POST("/login")
    public Call<PlayerModel> login(@Body PlayerModel player);
}
