package com.dasteam.quiz.quizgame.gui.premium;

import com.dasteam.quiz.quizgame.gui.premium.status.PremiumValidateStatus;

public interface PremiumCallback {
    void onBuyPremiumAttempt(PremiumValidateStatus status);
}
