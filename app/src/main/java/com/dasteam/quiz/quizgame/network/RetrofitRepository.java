package com.dasteam.quiz.quizgame.network;

import android.support.annotation.NonNull;
import android.util.Log;

import com.dasteam.quiz.quizgame.model.PlayerModel;
import com.dasteam.quiz.quizgame.network.call.LoginCall;
import com.dasteam.quiz.quizgame.network.call.PremiumCall;
import com.dasteam.quiz.quizgame.network.call.RegisterCall;

import java.net.HttpURLConnection;

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
                if (response.code() == HttpURLConnection.HTTP_OK && response.isSuccessful()) {
                    retriever.onDataRetrieved(response.body());
                } else {
                    retriever.onDataFailed(response.message(), response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<PlayerModel> call, @NonNull Throwable t) {
                retriever.onDataFailed(t.getMessage(), HttpURLConnection.HTTP_BAD_REQUEST);

            }
        });


    }

    public void register(String username, String password, DataRetriever<PlayerModel> retriever) {
        PlayerModel player = new PlayerModel();
        player.setUsername(username);
        player.setPassword(password);

        Call<PlayerModel> call = RetrofitService.getInstance().getRetrofit().create(RegisterCall.class).register(player);

        call.enqueue(new Callback<PlayerModel>() {
            @Override
            public void onResponse(@NonNull Call<PlayerModel> call, @NonNull Response<PlayerModel> response) {
                if (response.code() == HttpURLConnection.HTTP_OK && response.isSuccessful()) {
                    retriever.onDataRetrieved(response.body());
                } else {
                    retriever.onDataFailed(response.message(), response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<PlayerModel> call, @NonNull Throwable t) {
                retriever.onDataFailed(t.getMessage(), HttpURLConnection.HTTP_BAD_REQUEST);

            }
        });

    }

    public void makePremium(String id, DataRetriever<PlayerModel> retriever) {
        Call<PlayerModel> call = RetrofitService.getInstance().getRetrofit().create(PremiumCall.class).makePremium(id);
        call.enqueue(new Callback<PlayerModel>() {
            @Override
            public void onResponse(@NonNull Call<PlayerModel> call, @NonNull Response<PlayerModel> response) {
                if (response.code() == HttpURLConnection.HTTP_OK && response.isSuccessful()) {
                    retriever.onDataRetrieved(response.body());
                } else {
                    retriever.onDataFailed(response.message(), response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<PlayerModel> call, @NonNull Throwable t) {
                retriever.onDataFailed(t.getMessage(), HttpURLConnection.HTTP_BAD_REQUEST);
            }
        });
    }
}
