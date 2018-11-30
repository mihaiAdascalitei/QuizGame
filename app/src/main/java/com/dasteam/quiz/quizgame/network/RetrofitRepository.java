package com.dasteam.quiz.quizgame.network;

import android.support.annotation.NonNull;

import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.model.powerups.PowerUpsModel;
import com.dasteam.quiz.quizgame.network.call.BuyPowerUpsCall;
import com.dasteam.quiz.quizgame.network.call.LoginCall;
import com.dasteam.quiz.quizgame.network.call.PlayerPowerUpsCall;
import com.dasteam.quiz.quizgame.network.call.PremiumCall;
import com.dasteam.quiz.quizgame.network.call.RegisterCall;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitRepository {

    private static RetrofitRepository INSTANCE;

    public static RetrofitRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RetrofitRepository();
        }
        return INSTANCE;
    }

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

    public void getPowerUps(DataRetriever<List<PowerUpsModel>> retriever) {
        Call<List<PowerUpsModel>> call = RetrofitService.getInstance().getRetrofit().create(BuyPowerUpsCall.class).getPowerUps();
        call.enqueue(new Callback<List<PowerUpsModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<PowerUpsModel>> call, @NonNull Response<List<PowerUpsModel>> response) {
                if (response.code() == HttpURLConnection.HTTP_OK && response.isSuccessful()) {
                    retriever.onDataRetrieved(response.body());
                } else {
                    retriever.onDataFailed(response.message(), response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<PowerUpsModel>> call, Throwable t) {
                retriever.onDataFailed(t.getMessage(), HttpURLConnection.HTTP_BAD_REQUEST);

            }
        });
    }

    public void getPlayerPowerUps(String id, DataRetriever<List<PowerUpsModel>> retriever) {
        Call<List<PowerUpsModel>> call = RetrofitService.getInstance().getRetrofit().create(PlayerPowerUpsCall.class).getPlayerPowerUps(id);
        call.enqueue(new Callback<List<PowerUpsModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<PowerUpsModel>> call, @NonNull Response<List<PowerUpsModel>> response) {
                if (response.code() == HttpURLConnection.HTTP_OK && response.isSuccessful()) {
                    retriever.onDataRetrieved(response.body());
                } else {
                    retriever.onDataFailed(response.message(), response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<PowerUpsModel>> call, @NonNull Throwable t) {
                retriever.onDataFailed(t.getMessage(), HttpURLConnection.HTTP_BAD_REQUEST);
            }
        });
    }
}
