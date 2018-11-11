package com.dasteam.quiz.quizgame.gui.register;

import android.text.TextUtils;

import com.dasteam.quiz.quizgame.gui.register.status.RegisterResponseStatus;

public class RegisterController {

    public void register(String username, String password, String confirm, boolean acceptedTerms, RegisterCallback callback) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirm)) {
            callback.onRegisterAttempt(RegisterResponseStatus.EMPTY);
        } else if (username.length() < 5 || password.length() < 5) {
            callback.onRegisterAttempt(RegisterResponseStatus.LENGTH);
        } else if (!password.equals(confirm)) {
            callback.onRegisterAttempt(RegisterResponseStatus.PASSWORD_NO_MATCH);
        } else if (!acceptedTerms) {
            callback.onRegisterAttempt(RegisterResponseStatus.CHECK_BOX);
        } else {
            callback.onRegisterAttempt(RegisterResponseStatus.SUCCESS);
        }
    }
}
