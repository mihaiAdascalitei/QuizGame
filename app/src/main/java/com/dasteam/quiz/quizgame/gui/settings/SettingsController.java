package com.dasteam.quiz.quizgame.gui.settings;

import com.dasteam.quiz.quizgame.network.DataRetriever;
import com.dasteam.quiz.quizgame.network.RetrofitRepository;

public class SettingsController {
    private RetrofitRepository repository;

    public SettingsController() {
        repository = new RetrofitRepository();
    }

    public void removeAccount(String id, DataRetriever<String> retriever) {
        repository.removeAccount(id, retriever);
    }
}
