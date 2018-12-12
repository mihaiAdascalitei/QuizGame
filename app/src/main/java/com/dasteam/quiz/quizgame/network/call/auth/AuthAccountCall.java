package com.dasteam.quiz.quizgame.network.call.auth;

import com.dasteam.quiz.quizgame.model.player.PlayerModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AuthAccountCall {
    @POST("/login")
    Call<PlayerModel> login(@Body PlayerModel player);


    @POST("/register")
    Call<PlayerModel> register(@Body PlayerModel player);


    @DELETE("/removeAccount/{id}")
    Call<String> removeAccount(@Path("id") String playerId);
}
