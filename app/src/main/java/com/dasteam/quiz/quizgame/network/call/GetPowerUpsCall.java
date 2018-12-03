package com.dasteam.quiz.quizgame.network.call;

import com.dasteam.quiz.quizgame.model.powerups.PowerUpsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetPowerUpsCall {
    @GET("/getPowerUps")
    public Call<List<PowerUpsModel>> getPowerUps();
}
