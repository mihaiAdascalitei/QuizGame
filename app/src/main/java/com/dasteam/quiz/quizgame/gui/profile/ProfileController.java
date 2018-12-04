package com.dasteam.quiz.quizgame.gui.profile;

import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.provider.QuizProvider;

public class ProfileController {

    public void updatePlayer(PlayerModel player) {
        QuizProvider.provideIoManager().savePlayer(player);
    }

    public PlayerModel getPlayer() {
        return QuizProvider.provideIoManager().getPlayer();

    }
}
