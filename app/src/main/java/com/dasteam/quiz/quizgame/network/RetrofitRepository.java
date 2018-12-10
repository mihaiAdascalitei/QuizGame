package com.dasteam.quiz.quizgame.network;

import android.support.annotation.NonNull;

import com.dasteam.quiz.quizgame.model.feedback.FeedbackModel;
import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.model.powerups.PowerUpsModel;
import com.dasteam.quiz.quizgame.network.call.BuyPowerUpsCall;
import com.dasteam.quiz.quizgame.network.call.CheckPlayerPowerCall;
import com.dasteam.quiz.quizgame.network.call.GetPowerUpsCall;
import com.dasteam.quiz.quizgame.network.call.LoginCall;
import com.dasteam.quiz.quizgame.network.call.PlayerFeedbackCall;
import com.dasteam.quiz.quizgame.network.call.PlayerPowerUpsCall;
import com.dasteam.quiz.quizgame.network.call.PremiumCall;
import com.dasteam.quiz.quizgame.network.call.RankingPlayersCall;
import com.dasteam.quiz.quizgame.network.call.RegisterCall;
import com.dasteam.quiz.quizgame.network.call.RemoveAccountCall;
import com.dasteam.quiz.quizgame.network.call.RemoveFeedbackCall;
import com.dasteam.quiz.quizgame.network.call.ResetPasswordCall;
import com.dasteam.quiz.quizgame.network.call.SellPowerUpsCall;
import com.dasteam.quiz.quizgame.network.call.SendFeedbackCall;
import com.dasteam.quiz.quizgame.network.call.UpdateCreditCall;
import com.dasteam.quiz.quizgame.network.call.UpdatePointsCall;

import java.net.HttpURLConnection;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.dasteam.quiz.quizgame.utils.QuizUtils.dateString;

public class RetrofitRepository {

    private static RetrofitRepository INSTANCE;
    private Retrofit retrofit;

    public RetrofitRepository() {
        retrofit = RetrofitService.getInstance().getRetrofit();
    }

    public static RetrofitRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RetrofitRepository();
        }
        return INSTANCE;
    }

    public void login(String username, String password, DataRetriever<PlayerModel> retriever) {
        PlayerModel player = new PlayerModel(username, password);
        Call<PlayerModel> call = retrofit.create(LoginCall.class).login(player);
        requestExecute(call, retriever);

    }

    public void register(String username, String password, DataRetriever<PlayerModel> retriever) {
        PlayerModel player = new PlayerModel(username, password);
        Call<PlayerModel> call = retrofit.create(RegisterCall.class).register(player);
        requestExecute(call, retriever);

    }

    public void makePremium(int premium, String id, DataRetriever<PlayerModel> retriever) {
        Call<PlayerModel> call = retrofit.create(PremiumCall.class).makePremium(premium, id, dateString(new Date()));
        requestExecute(call, retriever);
    }

    public void getPowerUps(DataRetriever<List<PowerUpsModel>> retriever) {
        Call<List<PowerUpsModel>> call = retrofit.create(GetPowerUpsCall.class).getPowerUps();
        requestExecute(call, retriever);
    }

    public void getPlayerPowerUps(String id, DataRetriever<List<PowerUpsModel>> retriever) {
        Call<List<PowerUpsModel>> call = retrofit.create(PlayerPowerUpsCall.class).getPlayerPowerUps(id);
        requestExecute(call, retriever);
    }

    public void sellPowerUps(String playerId, String powerId, String powerCount, DataRetriever<List<PowerUpsModel>> retriever) {
        PowerUpsModel power = new PowerUpsModel(playerId, powerId, powerCount);
        Call<List<PowerUpsModel>> call = retrofit.create(SellPowerUpsCall.class).sellPowerUps(power);
        requestExecute(call, retriever);

    }

    public void updateCredit(String playerId, String credit, DataRetriever<PlayerModel> retriever) {
        Call<PlayerModel> call = retrofit.create(UpdateCreditCall.class).updatePlayerCredit(playerId, credit);
        requestExecute(call, retriever);
    }

    public void buyPowerUps(String playerId, String powerId, String powerCount, DataRetriever<List<PowerUpsModel>> retriever) {
        PowerUpsModel power = new PowerUpsModel(playerId, powerId, powerCount);
        Call<List<PowerUpsModel>> call = retrofit.create(BuyPowerUpsCall.class).buyPowerUps(power);
        requestExecute(call, retriever);
    }

    public void checkPlayerPowerUp(String playerId, String powerId, DataRetriever<List<PowerUpsModel>> retriever) {
        Call<List<PowerUpsModel>> call = retrofit.create(CheckPlayerPowerCall.class).checkPlayerPower(playerId, powerId);
        requestExecute(call, retriever);
    }


    public void resetPassword(String id, String password, DataRetriever<PlayerModel> retriever) {
        Call<PlayerModel> call = retrofit.create(ResetPasswordCall.class).resetPassword(id, password);
        requestExecute(call, retriever);
    }

    public void removeAccount(String id, DataRetriever<String> retriever) {
        Call<String> call = retrofit.create(RemoveAccountCall.class).removeAccount(id);
        requestExecute(call, retriever);
    }


    public void sendFeedback(String playerId, String category, String description, DataRetriever<String> retriever) {
        FeedbackModel feedback = new FeedbackModel(playerId, category, description, dateString(new Date()));
        Call<String> call = retrofit.create(SendFeedbackCall.class).sendFeedback(feedback);
        requestExecute(call, retriever);
    }

    public void getPlayerFeedback(String id, DataRetriever<List<FeedbackModel>> retriever) {
        Call<List<FeedbackModel>> call = retrofit.create(PlayerFeedbackCall.class).getPlayerFeedback(id);
        requestExecute(call, retriever);
    }


    public void removeFeedback(String feedbackId, String playerId, DataRetriever<List<FeedbackModel>> retriever) {
        Call<List<FeedbackModel>> call = retrofit.create(RemoveFeedbackCall.class).removeFeedback(feedbackId, playerId);
        requestExecute(call, retriever);
    }


    public void getRankingPlayers(DataRetriever<List<PlayerModel>> retriever) {
        Call<List<PlayerModel>> call = retrofit.create(RankingPlayersCall.class).getRankingPlayers();
        requestExecute(call, retriever);
    }


    public void updatePoints(String playerId, String points, DataRetriever<PlayerModel> retriever) {
        Call<PlayerModel> call = retrofit.create(UpdatePointsCall.class).updatePoints(playerId, points);
        requestExecute(call, retriever);
    }


    private <T> void requestExecute(Call<T> call, DataRetriever<T> retriever) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                if (response.code() == HttpURLConnection.HTTP_OK && response.isSuccessful()) {
                    retriever.onDataRetrieved(response.body());
                } else {
                    retriever.onDataFailed(response.message(), response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                retriever.onDataFailed(t.getMessage(), HttpURLConnection.HTTP_BAD_REQUEST);

            }
        });
    }
}