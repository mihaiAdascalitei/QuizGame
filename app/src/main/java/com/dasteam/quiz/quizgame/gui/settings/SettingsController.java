package com.dasteam.quiz.quizgame.gui.settings;

import com.dasteam.quiz.quizgame.network.DataRetriever;

import static com.dasteam.quiz.quizgame.provider.QuizProvider.provideRepository;

public class SettingsController {

    public void removeAccount(String id, DataRetriever<String> retriever) {
        provideRepository().removeAccount(id, retriever);
    }
}
