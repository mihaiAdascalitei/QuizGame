package com.dasteam.quiz.quizgame.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.dasteam.quiz.quizgame.R;

public abstract class BaseActivity extends AppCompatActivity {


    protected void onCreate(@Nullable Bundle savedInstanceState, int layout) {
        super.onCreate(savedInstanceState);
        setContentView(layout);
        attachViews();
        attachController();
        setListeners();
    }

    protected abstract void attachViews();

    protected abstract void attachController();

    protected abstract void setListeners();


    public void showAlert(String message){
        new AlertDialog
                .Builder(this)
                .setTitle(getString(R.string.alert_title))
                .setPositiveButton(getString(R.string.button_ok),null)
                .setMessage(message)
                .create()
                .show();
    }

}
