package com.dasteam.quiz.quizgame.network;

import com.dasteam.quiz.quizgame.model.PlayerModel;
import com.dasteam.quiz.quizgame.network.call.LoginCall;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitRepository {

    public void login(String username, String password, DataRetriever<PlayerModel> retriever) {
        Call<PlayerModel> call = RetrofitService.getInstance().getRetrofit().create(LoginCall.class).login(username, password);

        call.enqueue(new Callback<PlayerModel>() {
            @Override
            public void onResponse(Call<PlayerModel> call, Response<PlayerModel> response) {
                retriever.onDataRetrieved(response.body(), response.code());
            }

            @Override
            public void onFailure(Call<PlayerModel> call, Throwable t) {
                retriever.onDataFailed(t);

            }
        });


    }
}
