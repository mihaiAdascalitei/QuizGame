package com.dasteam.quiz.quizgame.network;

import android.support.annotation.NonNull;

import com.dasteam.quiz.quizgame.gui.powerups.buypowerups.BuyPowerUpsActivity;
import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.model.powerups.PowerUpsModel;
import com.dasteam.quiz.quizgame.network.call.BuyPowerUpsCall;
import com.dasteam.quiz.quizgame.network.call.CheckPlayerPowerCall;
import com.dasteam.quiz.quizgame.network.call.GetPowerUpsCall;
import com.dasteam.quiz.quizgame.network.call.LoginCall;
import com.dasteam.quiz.quizgame.network.call.PlayerPowerUpsCall;
import com.dasteam.quiz.quizgame.network.call.PremiumCall;
import com.dasteam.quiz.quizgame.network.call.RegisterCall;
import com.dasteam.quiz.quizgame.network.call.SellPowerUpsCall;
import com.dasteam.quiz.quizgame.network.call.UpdateCreditCall;
import com.dasteam.quiz.quizgame.utils.QuizUtils;

import java.net.HttpURLConnection;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dasteam.quiz.quizgame.utils.QuizUtils.dateString;

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

    public void makePremium(int premium, String id, DataRetriever<PlayerModel> retriever) {
        Call<PlayerModel> call = RetrofitService.getInstance().getRetrofit().create(PremiumCall.class).makePremium(premium, id, dateString(new Date()));
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
        Call<List<PowerUpsModel>> call = RetrofitService.getInstance().getRetrofit().create(GetPowerUpsCall.class).getPowerUps();
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

    public void sellPowerUps(String playerId, String powerId, String powerCount, DataRetriever<List<PowerUpsModel>> retriever) {
        PowerUpsModel power = new PowerUpsModel();
        power.setPlayerId(playerId);
        power.setPowerId(powerId);
        power.setPowerCount(powerCount);
        Call<List<PowerUpsModel>> call = RetrofitService.getInstance().getRetrofit().create(SellPowerUpsCall.class).sellPowerUps(power);

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

    public void updateCredit(String playerId, String credit, DataRetriever<PlayerModel> retriever) {
        Call<PlayerModel> call = RetrofitService.getInstance().getRetrofit().create(UpdateCreditCall.class).updatePlayerCredit(playerId, credit);
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

    public void buyPowerUps(String playerId, String powerId, String powerCount, DataRetriever<List<PowerUpsModel>> retriever) {
        PowerUpsModel power = new PowerUpsModel();
        power.setPlayerId(playerId);
        power.setPowerId(powerId);
        power.setPowerCount(powerCount);
        Call<List<PowerUpsModel>> call = RetrofitService.getInstance().getRetrofit().create(BuyPowerUpsCall.class).buyPowerUps(power);

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

    public void checkPlayerPowerUp(String playerId, String powerId, DataRetriever<List<PowerUpsModel>> retriever) {
        Call<List<PowerUpsModel>> call = RetrofitService.getInstance().getRetrofit().create(CheckPlayerPowerCall.class).checkPlayerPower(playerId, powerId);
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


