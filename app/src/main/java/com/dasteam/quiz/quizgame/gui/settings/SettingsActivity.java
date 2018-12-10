package com.dasteam.quiz.quizgame.gui.settings;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;
import com.dasteam.quiz.quizgame.gui.about.AboutActivity;
import com.dasteam.quiz.quizgame.gui.feedback.FeedbackActivity;
import com.dasteam.quiz.quizgame.gui.login.LoginActivity;
import com.dasteam.quiz.quizgame.gui.profile.background.LogoutTask;
import com.dasteam.quiz.quizgame.gui.terms.TermsAndConditionsActivity;
import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;

public class SettingsActivity extends BaseActivity {

    public static final String SETTINGS_PLAYER = "SETTINGS_PLAYER";

    private SettingsController settingsController;
    private PlayerModel player;
    private TextView tvFeedback;
    private TextView tvAbout;
    private TextView tvTerms;
    private Button btnRemoveAccount;


    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_settings);
        configureToolbar(getString(R.string.settings));
        getExtraData();
    }

    @Override
    protected void attachViews() {
        tvAbout = findViewById(R.id.tv_settings_about);
        tvFeedback = findViewById(R.id.tv_settings_feedback);
        tvTerms = findViewById(R.id.tv_settings_terms);
        btnRemoveAccount = findViewById(R.id.btn_settings_remove_account);
    }

    @Override
    protected void attachController() {
        settingsController = new SettingsController();
    }

    @Override
    protected void setListeners() {
        btnRemoveAccount.setOnClickListener(v -> showRemoveAccountAlert());
        tvFeedback.setOnClickListener(v -> openFeedbackScreen());
        tvTerms.setOnClickListener(v -> openTermsScreen());
        tvAbout.setOnClickListener(v -> openAboutScreen());
    }

    private void getExtraData() {
        player = (PlayerModel) getIntent().getSerializableExtra(SETTINGS_PLAYER);
    }

    private void showRemoveAccountAlert() {
        new AlertDialog.Builder(this, R.style.AlertDialogStyle)
                .setTitle(getString(R.string.alert_title))
                .setMessage(getString(R.string.remove_account_message))
                .setPositiveButton(getString(R.string.button_ok), (dialog, which) -> {
                    removeAccount();
                })
                .setNegativeButton(getString(R.string.cancel), null)
                .create()
                .show();
    }

    private void removeAccount() {
        showLoading(true);
        settingsController.removeAccount(player.getId(), new DataRetriever<String>() {
            @Override
            public void onDataRetrieved(String data) {
                logout();
            }

            @Override
            public void onDataFailed(String message, int code) {
                showLoading(false);
                showAlert(getString(R.string.default_alert));
            }
        });
    }

    private void logout() {
        new LogoutTask(() -> {
            showLoading(false);
            openLoginScreen();
        }).execute();
    }

    private void openLoginScreen() {
        finishAffinity();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void openFeedbackScreen() {
        startActivity(new Intent(this, FeedbackActivity.class).putExtra(FeedbackActivity.FEEDBACK_PLAYER, player));
    }

    private void openTermsScreen() {
        startActivity(new Intent(this, TermsAndConditionsActivity.class));
    }

    private void openAboutScreen() {
        startActivity(new Intent(this, AboutActivity.class).putExtra(AboutActivity.ABOUT_PLAYER, player));
    }
}
