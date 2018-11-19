package com.dasteam.quiz.quizgame.gui.lobby;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;

public class LobbyActivity extends BaseActivity {

    private LobbyController lobbyController;

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_lobby);
    }

    @Override
    protected void attachViews() {

    }

    @Override
    protected void attachController() {
        lobbyController = new LobbyController();
    }

    @Override
    protected void setListeners() {

    }
}
