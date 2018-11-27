package com.dasteam.quiz.quizgame.gui.profile;

import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.provider.QuizProvider;

public class ProfileController {

    public void updatePlayer(PlayerModel player) {
        QuizProvider.provideIoManager().savePlayer(player);
    }

    public void getPlayer(CacheProfileCallback callback) {
        PlayerModel player = QuizProvider.provideIoManager().getPlayer();
        callback.getCachedPlayer(player);
    }
}
