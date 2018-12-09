package com.dasteam.quiz.quizgame.gui.mainscreen;

import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.provider.QuizProvider;

import static com.dasteam.quiz.quizgame.provider.QuizProvider.provideIoManager;

public class MainScreenController {
    public PlayerModel getPlayer() {
        return provideIoManager().getPlayer();

    }
}
