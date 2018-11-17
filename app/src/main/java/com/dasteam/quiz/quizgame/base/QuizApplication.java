package com.dasteam.quiz.quizgame.base;

import android.app.Application;

public class QuizApplication extends Application {

    private static QuizApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static QuizApplication getApplication(){
        return application;
    }

}
