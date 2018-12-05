package com.dasteam.quiz.quizgame.gui.premium;

import android.text.TextUtils;

import com.dasteam.quiz.quizgame.gui.premium.status.PremiumValidateStatus;
import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;
import com.dasteam.quiz.quizgame.network.RetrofitRepository;
import com.dasteam.quiz.quizgame.provider.QuizProvider;

import static com.dasteam.quiz.quizgame.utils.QuizUtils.isEmpty;

public class PremiumAccountController {

    private RetrofitRepository repository;

    public PremiumAccountController() {
        repository = new RetrofitRepository();
    }

    public void validateData(String cardNumber, String ccv, String expDate, PremiumCallback callback) {
        if (cardNumber.trim().length() != 16) {
            callback.onBuyPremiumAttempt(PremiumValidateStatus.CREDIT_CARD_LENGTH);
        } else if (isEmpty(expDate)) {
            callback.onBuyPremiumAttempt(PremiumValidateStatus.EMPTY_DATE);
        } else if (ccv.trim().length() != 3) {
            callback.onBuyPremiumAttempt(PremiumValidateStatus.CCV_LENGTH);
        } else {
            callback.onBuyPremiumAttempt(PremiumValidateStatus.SUCCESS);
        }
    }

    public void makePremium(String id, DataRetriever<PlayerModel> retriever) {
        repository.makePremium(id, retriever);
    }

    public void cachePlayer(PlayerModel player) {
        QuizProvider.provideIoManager().savePlayer(player);
    }
}
