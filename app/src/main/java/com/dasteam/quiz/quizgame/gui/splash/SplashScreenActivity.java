package com.dasteam.quiz.quizgame.gui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.gui.login.LoginActivity;
import com.dasteam.quiz.quizgame.gui.mainscreen.MainScreenActivity;
import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;
import com.dasteam.quiz.quizgame.provider.QuizProvider;

import java.security.Provider;

import static com.dasteam.quiz.quizgame.gui.premium.PremiumAccountActivity.MAX_PREMIUM_DAY_LIMIT;
import static com.dasteam.quiz.quizgame.utils.QuizUtils.getDaysDifference;

public class SplashScreenActivity extends AppCompatActivity {

    private SplashScreenController controller;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = new SplashScreenController();
        checkCachedPlayer();
    }

    private void startMainScreen() {
        startActivity(new Intent(this,
                MainScreenActivity.class));
        finish();
    }

    private void startLoginScreen() {
        startActivity(new Intent(this,
                LoginActivity.class));
        finish();
    }

    private void checkCachedPlayer() {

        PlayerModel player = controller.getPlayer();
        if (player != null) {
            checkPremiumExpiration(player);
        } else {
            startLoginScreen();
        }
    }

    private void checkPremiumExpiration(PlayerModel player) {
        if (player.hasPremium()) {
            if (player.getPremiumDateActivated() != null) {
                if (player.hasPremium()) {
                    String premiumDate = player.getPremiumDateActivated();
                    if (premiumDate != null) {
                        int differenceDay = MAX_PREMIUM_DAY_LIMIT - getDaysDifference(premiumDate);
                        if (differenceDay == 0) {
                            disablePremium(player);
                        } else {
                            startMainScreen();
                        }
                    }
                }
            }
        } else {
            startMainScreen();
        }
    }

    private void disablePremium(PlayerModel player) {
        controller.disablePremium(player.getId(), new DataRetriever<PlayerModel>() {
            @Override
            public void onDataRetrieved(PlayerModel data) {
                controller.cachePlayer(data);
                startMainScreen();
            }

            @Override
            public void onDataFailed(String message, int code) {
                startMainScreen();
            }
        });
    }
}
