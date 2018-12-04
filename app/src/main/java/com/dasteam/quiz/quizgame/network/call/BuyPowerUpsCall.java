package com.dasteam.quiz.quizgame.network.call;

import com.dasteam.quiz.quizgame.model.powerups.PowerUpsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BuyPowerUpsCall {
    @POST("/buyPowerUps")
    public Call<List<PowerUpsModel>> buyPowerUps(@Body PowerUpsModel power);
}
