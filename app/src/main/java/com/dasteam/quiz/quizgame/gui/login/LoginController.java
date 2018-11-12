package com.dasteam.quiz.quizgame.gui.login;

import android.text.TextUtils;

import com.dasteam.quiz.quizgame.gui.login.status.LoginResponseStatus;
import com.dasteam.quiz.quizgame.model.PlayerModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;
import com.dasteam.quiz.quizgame.network.RetrofitRepository;

public class LoginController {

    private RetrofitRepository repository;

    public LoginController() {
        repository = new RetrofitRepository();
    }

    public void validateData(String username, String password, ControllerCallback callback) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            callback.onLoginAttempt(LoginResponseStatus.EMPTY);

        } else if (username.length() < 5 || password.length() < 5) {
            callback.onLoginAttempt(LoginResponseStatus.LENGTH);
        } else {
            callback.onLoginAttempt(LoginResponseStatus.SUCCESS);
        }
    }

    public void login(String username, String password, DataRetriever<PlayerModel> retriever) {
        repository.login(username, password, retriever);
    }

}
