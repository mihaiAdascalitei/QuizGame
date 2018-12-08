package com.dasteam.quiz.quizgame.network.call;

import com.dasteam.quiz.quizgame.model.player.PlayerModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ResetPasswordCall {

    @GET("/resetPassword/{id}/{password}")
    Call<PlayerModel> resetPassword(@Path("id") String id,
                                    @Path("password") String password);
}
