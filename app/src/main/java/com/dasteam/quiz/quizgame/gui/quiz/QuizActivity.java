package com.dasteam.quiz.quizgame.gui.quiz;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;
import com.dasteam.quiz.quizgame.gui.quiz.options.OptionsFragment;

public class QuizActivity extends BaseActivity {

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_quiz);
        startFragment(new OptionsFragment());
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

    private void startFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.quiz_container, fragment).commit();
    }
}
