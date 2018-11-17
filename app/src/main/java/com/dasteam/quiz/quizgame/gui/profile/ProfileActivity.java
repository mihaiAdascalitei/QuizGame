package com.dasteam.quiz.quizgame.gui.profile;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;

public class ProfileActivity extends BaseActivity {

    private ProfileController profileController;

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_profile);
    }

    @Override
    protected void attachViews() {

    }

    @Override
    protected void attachController() {
        profileController = new ProfileController();
    }

    @Override
    protected void setListeners() {

    }
}
