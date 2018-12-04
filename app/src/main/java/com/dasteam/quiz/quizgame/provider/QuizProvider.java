package com.dasteam.quiz.quizgame.provider;

import android.content.Context;

import com.dasteam.quiz.quizgame.base.QuizApplication;
import com.dasteam.quiz.quizgame.network.RetrofitRepository;

public class QuizProvider {
    private static QuizProvider INSTANCE;

    public static QuizProvider newInstance() {
        if (INSTANCE == null) {
            INSTANCE = new QuizProvider();
        }

        return INSTANCE;
    }

    public static Context provideAppContext() {
        return QuizApplication.getApplication().getApplicationContext();
    }

    public static IOManager provideIoManager() {
        return IOManager.getInstance();
    }

    public static RetrofitRepository provideRepository() {
        return RetrofitRepository.getInstance();
    }
}
