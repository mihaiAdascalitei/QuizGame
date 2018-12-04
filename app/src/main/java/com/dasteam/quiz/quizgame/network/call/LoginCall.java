package com.dasteam.quiz.quizgame.network.call;

import com.dasteam.quiz.quizgame.model.player.PlayerModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginCall {
    @POST("/login")
    public Call<PlayerModel> login(@Body PlayerModel player);
}
