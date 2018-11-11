package com.dasteam.quiz.quizgame.gui.login;

import android.text.TextUtils;

import com.dasteam.quiz.quizgame.gui.login.status.LoginResponseStatus;

public class LoginController {

    public void login(String username, String password, ControllerCallback callback) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            callback.onLoginAttempt(LoginResponseStatus.EMPTY);

        } else if (username.length() < 5 || password.length() < 5) {
            callback.onLoginAttempt(LoginResponseStatus.LENGTH);
        } else {
            callback.onLoginAttempt(LoginResponseStatus.NOT_FOUND);
        }
    }

}
