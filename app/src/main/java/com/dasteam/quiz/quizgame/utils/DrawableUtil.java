package com.dasteam.quiz.quizgame.utils;

import android.content.Context;

public class DrawableUtil {


    public static int resIdByName(Context context, String resIdName) {
        return context.getResources().getIdentifier(resIdName, "drawable", context.getPackageName());
    }
}
