package com.dasteam.quiz.quizgame.gui.resetpassword;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;
import com.dasteam.quiz.quizgame.gui.resetpassword.status.ResetPasswordStatus;
import com.dasteam.quiz.quizgame.model.player.PlayerModel;

public class ResetPasswordActivity extends BaseActivity {
    public static String RESET_PASSWORD_PLAYER = "RESET_PASSWORD_PLAYER";

    private ResetPasswordController passwordController;
    private EditText etCurrentPassword;
    private EditText etNewPassword;
    private Button btnReset;
    private PlayerModel player;
    private TextView tvCurrentPassError;
    private TextView tvNewPassError;

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

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_reset_password);
        configureToolbar(getString(R.string.reset_password));
        getExtraData();

    }

    @Override
    protected void attachViews() {
        etCurrentPassword = findViewById(R.id.et_reset_current_password);
        etNewPassword = findViewById(R.id.et_reset_new_password);
        btnReset = findViewById(R.id.btn_reset);
        tvCurrentPassError = findViewById(R.id.tv_reset_password_error);
        tvNewPassError = findViewById(R.id.tv_reset_password_new_error);
    }

    @Override
    protected void attachController() {
        passwordController = new ResetPasswordController();
    }

    @Override
    protected void setListeners() {
        btnReset.setOnClickListener(v -> reset());
    }

    private void getExtraData() {
        player = (PlayerModel) getIntent().getSerializableExtra(RESET_PASSWORD_PLAYER);
    }

    private void reset() {
        String currentPassword = etCurrentPassword.getText().toString();
        String newPassword = etNewPassword.getText().toString();
        String playerPassword = player.getPassword();

        passwordController.validateData(playerPassword, currentPassword, newPassword, this::handlePasswordData);
    }

    private void handlePasswordData(ResetPasswordStatus status) {
        switch (status) {
            case SUCCESS:
                resetPassword();
                break;
            case EMPTY:
                setNewPassAlert(getString(R.string.password_empty_error));
                break;
            case LENGTH:
                setNewPassAlert(getString(R.string.username_length_error));
                break;
            case SAME_PASSWORD:
                setNewPassAlert(getString(R.string.same_password_error));
                break;
            case WRONG_CURRENT:
                setCurrentAlert(getString(R.string.account_password_error));
                break;
            default:
                break;
        }
    }

    private void setCurrentAlert(String message) {
        tvCurrentPassError.setText(message);
        tvCurrentPassError.setVisibility(View.VISIBLE);
        tvNewPassError.setVisibility(View.GONE);
    }

    private void setNewPassAlert(String message) {
        tvNewPassError.setText(message);
        tvNewPassError.setVisibility(View.VISIBLE);
        tvCurrentPassError.setVisibility(View.GONE);
    }

    private void hideAlert() {
        tvNewPassError.setVisibility(View.GONE);
        tvCurrentPassError.setVisibility(View.GONE);
    }

    private void resetPassword() {
        hideAlert();
    }

}
