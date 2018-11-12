package com.dasteam.quiz.quizgame.network.call;

import com.dasteam.quiz.quizgame.model.PlayerModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface LoginCall {

    @POST("/login")
    Call<PlayerModel> login(@Field("username") String username, @Field("password") String password);
}
