package com.dasteam.quiz.quizgame.gui.resetpassword;

import com.dasteam.quiz.quizgame.gui.resetpassword.status.ResetPasswordStatus;
import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;
import com.dasteam.quiz.quizgame.network.RetrofitRepository;
import com.dasteam.quiz.quizgame.provider.QuizProvider;

import static com.dasteam.quiz.quizgame.provider.QuizProvider.provideRepository;
import static com.dasteam.quiz.quizgame.utils.QuizUtils.isEmpty;

public class ResetPasswordController {

    public void validateData(String playerDataPassword, String currentPassword, String newPassword, ResetPasswordCallback callback) {
        if (!playerDataPassword.equals(currentPassword)) {
            callback.onResetAttempt(ResetPasswordStatus.WRONG_CURRENT);
        } else if (isEmpty(newPassword)) {
            callback.onResetAttempt(ResetPasswordStatus.EMPTY);
        } else if (currentPassword.equals(newPassword)) {
            callback.onResetAttempt(ResetPasswordStatus.SAME_PASSWORD);
        } else if (newPassword.length() < 5) {
            callback.onResetAttempt(ResetPasswordStatus.LENGTH);
        } else {
            callback.onResetAttempt(ResetPasswordStatus.SUCCESS);
        }
    }

    public void resetPassword(String playerId, String password, DataRetriever<PlayerModel> retriever) {
        provideRepository().resetPassword(playerId, password, retriever);
    }


    public void cachePlayer(PlayerModel player) {
        QuizProvider.provideIoManager().savePlayer(player);
    }

}
