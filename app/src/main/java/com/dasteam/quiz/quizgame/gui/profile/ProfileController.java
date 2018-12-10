package com.dasteam.quiz.quizgame.gui.profile;

import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.provider.QuizProvider;

import static com.dasteam.quiz.quizgame.provider.QuizProvider.provideIoManager;

public class ProfileController {

    public void updatePlayer(PlayerModel player) {
        provideIoManager().savePlayer(player);
    }

    public PlayerModel getPlayer() {
        return provideIoManager().getPlayer();

    }
}
