package com.dasteam.quiz.quizgame.gui.mainscreen.animate;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.gui.mainscreen.ClickHandler;

import static com.dasteam.quiz.quizgame.utils.Animator.animate;

public class AnimatorTouchListener implements View.OnTouchListener {
    private static final int CLICK_LONG_TRESHOLD = 200;

    private ClickHandler clickHandler;

    public AnimatorTouchListener(ClickHandler clickHandler) {
        this.clickHandler = clickHandler;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                animate(v, R.animator.resize_down);
                return true;
            case MotionEvent.ACTION_UP:
                animate(v, R.animator.resize_up);
                float duration = event.getEventTime() - event.getDownTime();
                if (duration < CLICK_LONG_TRESHOLD) {
                    clickHandler.onClick();
                }
                return true;
        }
        return false;
    }
}
