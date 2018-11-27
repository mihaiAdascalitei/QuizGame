package com.dasteam.quiz.quizgame.gui.login.status;

import com.dasteam.quiz.quizgame.model.player.PlayerModel;

public interface CachedPlayerCallback {
    void onPlayerAlreadyLogged(PlayerModel player);
}
