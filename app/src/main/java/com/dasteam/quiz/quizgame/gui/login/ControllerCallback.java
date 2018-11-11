package com.dasteam.quiz.quizgame.gui.login;

import com.dasteam.quiz.quizgame.gui.login.status.LoginResponseStatus;

public interface ControllerCallback {
    void onLoginAttempt(LoginResponseStatus response);
}
