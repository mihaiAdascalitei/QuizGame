package com.dasteam.quiz.quizgame.gui.resetpassword;

import com.dasteam.quiz.quizgame.gui.resetpassword.status.ResetPasswordStatus;

public interface ResetPasswordCallback {
    void onResetAttempt(ResetPasswordStatus status);
}
