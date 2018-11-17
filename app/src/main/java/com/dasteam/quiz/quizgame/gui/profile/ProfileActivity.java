package com.dasteam.quiz.quizgame.gui.profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;
import com.dasteam.quiz.quizgame.gui.login.LoginActivity;
import com.dasteam.quiz.quizgame.gui.profile.background.LogoutCallback;
import com.dasteam.quiz.quizgame.gui.profile.background.LogoutTask;

public class ProfileActivity extends BaseActivity {
    public static String PROFILE_PLAYER = "PROFILE_PLAYER";
    private ProfileController profileController;
    private Button btnLogout;

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_profile);
        configureToolbar(getString(R.string.profile));
    }

    @Override
    protected void attachViews() {
        btnLogout = findViewById(R.id.btn_logout);
    }

    @Override
    protected void attachController() {
        profileController = new ProfileController();
    }

    @Override
    protected void setListeners() {
        btnLogout.setOnClickListener(v -> logout());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_keyboard_backspace);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void logout() {
        showDialog(true);
        new LogoutTask(() -> {
            showDialog(false);
            openLoginScreen();
        }).execute();
    }

    private void openLoginScreen() {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
