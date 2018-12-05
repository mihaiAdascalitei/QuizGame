package com.dasteam.quiz.quizgame.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

public class QuizApplication extends Application {

    private static QuizApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static QuizApplication getApplication() {
        return application;
    }

}
