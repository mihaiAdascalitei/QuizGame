package com.dasteam.quiz.quizgame.utils;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

import com.dasteam.quiz.quizgame.provider.QuizProvider;

public class Animator {
    public static void animate(View v, int animation) {
        AnimatorSet regainer = (AnimatorSet) AnimatorInflater.loadAnimator(QuizProvider.provideAppContext(), animation);
        regainer.setTarget(v);
        regainer.start();
    }

    public static void rotate(View v){
        ObjectAnimator rotate = ObjectAnimator.ofFloat(v, "rotation", 0f, 180f);
        rotate.setDuration(300);
        rotate.start();
    }
}
