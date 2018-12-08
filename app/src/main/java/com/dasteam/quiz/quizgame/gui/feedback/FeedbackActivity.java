package com.dasteam.quiz.quizgame.gui.feedback;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;

public class FeedbackActivity extends BaseActivity {

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_feedback);
        configureToolbar(getString(R.string.settings_feedback));
    }

    @Override
    protected void attachViews() {

    }

    @Override
    protected void attachController() {

    }

    @Override
    protected void setListeners() {

    }
}
