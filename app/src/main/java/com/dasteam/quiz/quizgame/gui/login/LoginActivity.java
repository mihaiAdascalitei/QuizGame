package com.dasteam.quiz.quizgame.gui.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;
import com.dasteam.quiz.quizgame.gui.login.status.LoginResponseStatus;
import com.dasteam.quiz.quizgame.gui.register.RegisterActivity;

public class LoginActivity extends BaseActivity {

    private Button btnLogin;
    private EditText etUsername;
    private EditText etPassword;
    private TextView tvRegister;
    private LoginController loginController;
    private TextView tvAlertUsername;
    private TextView tvAlertPassword;


    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_login);
    }

    @Override
    protected void attachViews() {
        btnLogin = findViewById(R.id.btn_login);
        etUsername = findViewById(R.id.et_login_username);
        etPassword = findViewById(R.id.et_login_password);
        tvRegister = findViewById(R.id.tv_register);
        tvAlertPassword = findViewById(R.id.tv_login_alert_username);
        tvAlertUsername = findViewById(R.id.tv_login_alert_password);


    }

    @Override
    protected void attachController() {
        loginController = new LoginController();
    }


    @Override
    protected void setListeners() {
        btnLogin.setOnClickListener(v -> login());
        tvRegister.setOnClickListener(v -> startRegisterActivity());

    }

    private void startRegisterActivity() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    private void login() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        loginController.login(username, password, this::handleLoginResponse);
    }

    private void handleLoginResponse(LoginResponseStatus response) {
        switch (response) {
            case EMPTY:
                showEmptyErrorMessage();
                break;
            case LENGTH:
                showLengthErrorMessage();
                break;
            case SUCCESS:
                loginSuccess();
                break;
            case NOT_FOUND:
                showNotFoundErrorMessage();
                break;
            default:
                break;
        }
    }

    private void showEmptyErrorMessage() {
        setLoginAlert(true, true);
    }

    private void showLengthErrorMessage() {
        setLoginAlert(true, false);
    }

    private void showNotFoundErrorMessage() {
        setLoginAlert(false, false);
        showAlert(getString(R.string.user_not_exists));
    }

    private void loginSuccess() {
        setLoginAlert(false, false);
        //start home activity and pass user into it
    }

    private void setLoginAlert(boolean visible, boolean isEmpty) {
        tvAlertUsername.setVisibility(visible ? View.VISIBLE : View.GONE);
        tvAlertPassword.setVisibility(visible ? View.VISIBLE : View.GONE);
        tvAlertUsername.setText(getString(isEmpty ? R.string.username_empty_error : R.string.username_length_error));
        tvAlertPassword.setText(getString(isEmpty ? R.string.password_empty_error : R.string.username_length_error));
    }
}
