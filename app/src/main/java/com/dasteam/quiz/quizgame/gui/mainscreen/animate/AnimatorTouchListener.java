package com.dasteam.quiz.quizgame.gui.mainscreen.animate;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;

import com.dasteam.quiz.quizgame.R;

import static com.dasteam.quiz.quizgame.utils.Animator.animate;

public class AnimatorTouchListener implements View.OnTouchListener {
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                animate(v, R.animator.resize_down);
                return true;
            case MotionEvent.ACTION_UP:
                animate(v, R.animator.resize_up);

                return true;
        }
        return false;
    }
}
