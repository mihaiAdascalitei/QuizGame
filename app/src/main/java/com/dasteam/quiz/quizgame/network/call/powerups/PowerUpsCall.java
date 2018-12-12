package com.dasteam.quiz.quizgame.network.call.powerups;

import com.dasteam.quiz.quizgame.model.powerups.PowerUpsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PowerUpsCall {
    @POST("/buyPowerUps")
    Call<List<PowerUpsModel>> buyPowerUps(@Body PowerUpsModel power);


    @GET("/checkPlayerPowerUp/{playerId}/{powerId}")
    Call<List<PowerUpsModel>> checkPlayerPowerExists(@Path("playerId") String playerId,
                                                     @Path("powerId") String powerId);

    @GET("/getPowerUps")
    Call<List<PowerUpsModel>> getAllPowerUps();


    @GET("/getPlayerPowerUps/{id}")
    Call<List<PowerUpsModel>> getPlayerPowerUps(@Path("id") String id);


    @POST("/sellPowerUps")
    public Call<List<PowerUpsModel>> sellPowerUps(@Body PowerUpsModel power);
}
