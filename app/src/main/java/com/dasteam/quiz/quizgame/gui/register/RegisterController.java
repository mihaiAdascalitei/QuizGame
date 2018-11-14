package com.dasteam.quiz.quizgame.gui.register;

import android.text.TextUtils;

import com.dasteam.quiz.quizgame.gui.register.status.RegisterResponseStatus;
import com.dasteam.quiz.quizgame.model.PlayerModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;
import com.dasteam.quiz.quizgame.network.RetrofitRepository;

public class RegisterController {
    private RetrofitRepository repository;

    public RegisterController() {
        repository = new RetrofitRepository();
    }

    public void validateData(String username, String password, String confirm, boolean acceptedTerms, RegisterCallback callback) {
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

    public void register(String username, String password, DataRetriever<PlayerModel> retriever) {
        repository.register(username, password, retriever);
    }


}
