package com.dasteam.quiz.quizgame.gui.mainscreen;

import com.dasteam.quiz.quizgame.model.PlayerModel;
import com.dasteam.quiz.quizgame.provider.QuizProvider;

public class MainScreenController {
    public PlayerModel getPlayer() {
        return QuizProvider.provideIoManager().getPlayer();

    }
}
