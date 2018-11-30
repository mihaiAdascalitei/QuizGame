package com.dasteam.quiz.quizgame.network.call;

import com.dasteam.quiz.quizgame.model.powerups.PowerUpsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlayerPowerUpsCall {
    @GET("/getPlayerPowerUps/{id}")
    public Call<List<PowerUpsModel>> getPlayerPowerUps(@Path("id") String id);
}
