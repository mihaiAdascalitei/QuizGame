package com.dasteam.quiz.quizgame.gui.quiz.announcement;

import android.os.CountDownTimer;

import com.dasteam.quiz.quizgame.model.question.QuestionModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;
import com.dasteam.quiz.quizgame.network.RetrofitRepository;
import com.dasteam.quiz.quizgame.provider.QuizProvider;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static com.dasteam.quiz.quizgame.provider.QuizProvider.provideRepository;
import static com.dasteam.quiz.quizgame.utils.QuizUtils.string;

public class AnnouncementController {

    private CountDownTimer countDownTimer;


    public GameType generateRandomGame() {
        Random random = new Random();
        return random.nextInt(2) == 0 ? GameType.RANGE : GameType.VARIANTS;
    }

    public void setupCountDownTimer(AnnouncementCallback callback) {
        AtomicInteger time = new AtomicInteger(3); // seconds
        countDownTimer = new CountDownTimer(3000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                callback.onTick(string(time.get()));
                time.set(time.get() - 1);
            }

            @Override
            public void onFinish() {
                callback.onFinish();
            }
        }.start();

    }

    public void stopCountDown() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
