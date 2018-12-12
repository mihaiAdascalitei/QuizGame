package com.dasteam.quiz.quizgame.network.call.player;

import com.dasteam.quiz.quizgame.model.player.PlayerModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UpdatePlayerEntriesCall {
    @GET("/premium/{premium}/{id}/{date}")
    Call<PlayerModel> makePremium(@Path("premium") int premium,
                                  @Path("id") String id,
                                  @Path("date") String date);

    @GET("/updateCredit/{playerId}/{credit}")
    Call<PlayerModel> updatePlayerCredit(@Path("playerId") String playerId,
                                         @Path("credit") String credit);

    @GET("/updatePoints/{id}/{points}")
    Call<PlayerModel> updatePoints(@Path("id") String id,
                                   @Path("password") String points);

    @GET("/resetPassword/{id}/{password}")
    Call<PlayerModel> resetPassword(@Path("id") String id,
                                    @Path("password") String password);
}
