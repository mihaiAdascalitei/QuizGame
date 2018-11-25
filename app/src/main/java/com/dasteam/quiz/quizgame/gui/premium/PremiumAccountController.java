package com.dasteam.quiz.quizgame.gui.premium;

import android.text.TextUtils;

import com.dasteam.quiz.quizgame.gui.premium.status.PremiumValidateStatus;

public class PremiumAccountController {

    public void validateData(String cardNumber, String ccv, String expDate, PremiumCallback callback) {
        if (cardNumber.trim().length() != 16) {
            callback.onBuyPremiumAttempt(PremiumValidateStatus.CREDIT_CARD_LENGTH);
        } else if (TextUtils.isEmpty(expDate)) {
            callback.onBuyPremiumAttempt(PremiumValidateStatus.EMPTY_DATE);
        } else if (ccv.trim().length() != 3) {
            callback.onBuyPremiumAttempt(PremiumValidateStatus.CCV_LENGTH);
        } else {
            callback.onBuyPremiumAttempt(PremiumValidateStatus.SUCCESS);
        }
    }
}
