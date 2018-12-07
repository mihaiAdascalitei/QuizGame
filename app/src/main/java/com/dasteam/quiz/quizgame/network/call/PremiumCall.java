package com.dasteam.quiz.quizgame.network.call;

import com.dasteam.quiz.quizgame.model.player.PlayerModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PremiumCall {
    @GET("/premium/{premium}/{id}/{date}")
    Call<PlayerModel> makePremium(@Path("premium") int premium,
                                  @Path("id") String id,
                                  @Path("date") String date);
}
