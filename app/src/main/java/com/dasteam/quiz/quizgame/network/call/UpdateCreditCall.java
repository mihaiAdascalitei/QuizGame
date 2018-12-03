package com.dasteam.quiz.quizgame.network.call;

import com.dasteam.quiz.quizgame.model.player.PlayerModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UpdateCreditCall {
    @GET("/updateCredit/{playerId}/{credit}")
    public Call<PlayerModel> updatePlayerCredit(@Path("playerId") String playerId,
                                                @Path("credit") String credit);
}
