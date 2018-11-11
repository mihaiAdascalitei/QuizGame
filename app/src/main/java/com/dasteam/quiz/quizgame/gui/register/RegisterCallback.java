package com.dasteam.quiz.quizgame.gui.register;

import com.dasteam.quiz.quizgame.gui.register.status.RegisterResponseStatus;

public interface RegisterCallback {
    void onRegisterAttempt(RegisterResponseStatus status);
}
