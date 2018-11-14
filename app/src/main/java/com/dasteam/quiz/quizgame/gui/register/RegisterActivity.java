package com.dasteam.quiz.quizgame.gui.register;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;
import com.dasteam.quiz.quizgame.gui.login.LoginActivity;
import com.dasteam.quiz.quizgame.gui.register.status.RegisterResponseStatus;
import com.dasteam.quiz.quizgame.model.PlayerModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;

public class RegisterActivity extends BaseActivity {

    private Toolbar toolbar;
    private RegisterController registerController;
    private EditText etUsername;
    private EditText etPassword;
    private EditText etConfirm;
    private CheckBox cbTerms;
    private TextView tvAlertUsername;
    private TextView tvAlertPassword;
    private TextView tvAlertConfirm;

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_register);
        configureToolbar();
    }

    @Override
    protected void attachViews() {
        toolbar = findViewById(R.id.toolbar);
        etUsername = findViewById(R.id.et_register_username);
        etPassword = findViewById(R.id.et_register_password);
        etConfirm = findViewById(R.id.et_register_confirm);
        cbTerms = findViewById(R.id.cb_terms_and_conds);
        tvAlertConfirm = findViewById(R.id.tv_register_alert_confirm);
        tvAlertUsername = findViewById(R.id.tv_register_alert_username);
        tvAlertPassword = findViewById(R.id.tv_register_alert_password);
    }

    @Override
    protected void attachController() {
        registerController = new RegisterController();
    }

    @Override
    protected void setListeners() {

    }

    private void configureToolbar() {
        toolbar.setTitle(getString(R.string.register));
        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.register_menu, menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_close);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_save:
                save();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void save() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String confirm = etConfirm.getText().toString();

        registerController.validateData(username, password, confirm, cbTerms.isChecked(), this::handleRegisterResponse);
    }

    private void handleRegisterResponse(RegisterResponseStatus status) {
        switch (status) {
            case SUCCESS:
                saveSuccessfull();
                break;
            case LENGTH:
                showLengthError();
                break;
            case EMPTY:
                showEmptyError();
                break;
            case CHECK_BOX:
                showTermsError();
                break;
            case USERNAME_TAKEN:
                showusernameTakenError();
                break;
            case PASSWORD_NO_MATCH:
                showMissmatchPasswordError();
                break;
            default:
                break;
        }

    }

    private void saveSuccessfull() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        showDialog(true);
        registerController.register(username, password, new DataRetriever<PlayerModel>() {
            @Override
            public void onDataRetrieved(PlayerModel data) {
                showDialog(false);
                backToLogin(data);
            }

            @Override
            public void onDataFailed(String message, int code) {
                showDialog(false);
                showAlert(message);
            }
        });
    }

    private void backToLogin(PlayerModel player) {
        Intent intent = new Intent();
        intent.putExtra(LoginActivity.EXTRA_PLAYER_KEY, player);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void showLengthError() {
        setRegisterAlert(true, false);
    }

    private void showEmptyError() {
        setRegisterAlert(true, true);
    }

    private void showTermsError() {
        setRegisterAlert(false, false);
        showAlert(getString(R.string.terms_and_conds_error));
    }

    private void showMissmatchPasswordError() {
        tvAlertUsername.setVisibility(View.GONE);
        tvAlertPassword.setVisibility(View.VISIBLE);
        tvAlertConfirm.setVisibility(View.VISIBLE);
        tvAlertConfirm.setText(getString(R.string.password_match_error));
        tvAlertPassword.setText(getString(R.string.password_match_error));
    }

    private void showusernameTakenError() {
        setRegisterAlert(false, false);
        showAlert(getString(R.string.username_taken));
    }

    private void setRegisterAlert(boolean visible, boolean isEmpty) {
        tvAlertUsername.setVisibility(visible ? View.VISIBLE : View.GONE);
        tvAlertPassword.setVisibility(visible ? View.VISIBLE : View.GONE);
        tvAlertConfirm.setVisibility(visible ? View.VISIBLE : View.GONE);

        tvAlertUsername.setText(getString(isEmpty ? R.string.username_empty_error : R.string.username_length_error));
        tvAlertPassword.setText(getString(isEmpty ? R.string.password_empty_error : R.string.username_length_error));
        tvAlertConfirm.setText(getString(isEmpty ? R.string.password_empty_error : R.string.username_length_error));
    }


}
