package com.dasteam.quiz.quizgame.network;

import android.support.annotation.NonNull;

import com.dasteam.quiz.quizgame.model.PlayerModel;
import com.dasteam.quiz.quizgame.network.call.LoginCall;

import java.net.HttpURLConnection;
import java.net.URLConnection;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitRepository {

    public void login(String username, String password, DataRetriever<PlayerModel> retriever) {
        PlayerModel player = new PlayerModel();
        player.setUsername(username);
        player.setPassword(password);
        Call<PlayerModel> call = RetrofitService.getInstance().getRetrofit().create(LoginCall.class).login(player);

        call.enqueue(new Callback<PlayerModel>() {
            @Override
            public void onResponse(@NonNull Call<PlayerModel> call, @NonNull Response<PlayerModel> response) {
                retriever.onDataRetrieved(response.body(), response.code());
            }

            @Override
            public void onFailure(@NonNull Call<PlayerModel> call, Throwable t) {
                retriever.onDataFailed(t.getMessage());

            }
        });


    }
}
