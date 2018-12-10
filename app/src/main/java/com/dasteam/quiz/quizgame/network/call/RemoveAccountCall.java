package com.dasteam.quiz.quizgame.network.call;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public interface RemoveAccountCall {
    @DELETE("/removeAccount/{id}")
    Call<String> removeAccount(@Path("id") String playerId);
}
