package com.dasteam.quiz.quizgame.gui.login;


import com.dasteam.quiz.quizgame.gui.login.status.LoginResponseStatus;
import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;
import com.dasteam.quiz.quizgame.provider.QuizProvider;

import static com.dasteam.quiz.quizgame.provider.QuizProvider.provideRepository;
import static com.dasteam.quiz.quizgame.utils.QuizUtils.isEmpty;


public class LoginController {

    public void validateData(String username, String password, ControllerCallback callback) {
        if (isEmpty(username) || isEmpty(password)) {
            callback.onLoginAttempt(LoginResponseStatus.EMPTY);

        } else if (username.length() < 5 || password.length() < 5) {
            callback.onLoginAttempt(LoginResponseStatus.LENGTH);
        } else {
            callback.onLoginAttempt(LoginResponseStatus.SUCCESS);
        }
    }

    public void login(String username, String password, DataRetriever<PlayerModel> retriever) {
        provideRepository().login(username, password, retriever);
    }

    public void cachePlayer(PlayerModel player) {
        QuizProvider.provideIoManager().savePlayer(player);
    }
}
