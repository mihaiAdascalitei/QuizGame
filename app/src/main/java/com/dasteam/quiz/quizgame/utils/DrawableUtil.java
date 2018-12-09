package com.dasteam.quiz.quizgame.utils;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.View;

public class DrawableUtil {


    public static int resIdByName(Context context, String resIdName) {
        return context.getResources().getIdentifier(resIdName, "drawable", context.getPackageName());
    }

}
