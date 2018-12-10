package com.dasteam.quiz.quizgame.gui.register;

import android.text.TextUtils;

import com.dasteam.quiz.quizgame.gui.register.status.RegisterResponseStatus;
import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;
import com.dasteam.quiz.quizgame.network.RetrofitRepository;
import com.dasteam.quiz.quizgame.provider.QuizProvider;

import static com.dasteam.quiz.quizgame.provider.QuizProvider.provideRepository;
import static com.dasteam.quiz.quizgame.utils.QuizUtils.isEmpty;

public class RegisterController {

    public void validateData(String username, String password, String confirm, boolean acceptedTerms, RegisterCallback callback) {
        if (isEmpty(username) || isEmpty(password) || isEmpty(confirm)) {
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

    public void register(String username, String password, DataRetriever<PlayerModel> retriever) {
        provideRepository().register(username, password, retriever);
    }


}
