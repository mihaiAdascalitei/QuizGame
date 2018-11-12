package com.dasteam.quiz.quizgame.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.custom.LoadingDialog;

public abstract class BaseActivity extends AppCompatActivity {

    private LoadingDialog loading;

    protected void onCreate(@Nullable Bundle savedInstanceState, int layout) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(layout);
        attachViews();
        attachController();
        setListeners();
    }

    protected abstract void attachViews();

    protected abstract void attachController();

    protected abstract void setListeners();

    private void init() {
        loading = new LoadingDialog(this);
    }

    public void showAlert(String message) {
        new AlertDialog
                .Builder(this)
                .setTitle(getString(R.string.alert_title))
                .setPositiveButton(getString(R.string.button_ok), null)
                .setMessage(message)
                .create()
                .show();
    }

    protected void showDialog(boolean show) {
        if (show) {
            loading.show();
        } else {
            loading.cancel();
        }
    }

}
