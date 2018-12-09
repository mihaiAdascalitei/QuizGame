package com.dasteam.quiz.quizgame.gui.splash;

import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;
import com.dasteam.quiz.quizgame.network.RetrofitRepository;
import com.dasteam.quiz.quizgame.provider.QuizProvider;

import static com.dasteam.quiz.quizgame.provider.QuizProvider.provideRepository;

public class SplashScreenController {

    public PlayerModel getPlayer() {
        return QuizProvider.provideIoManager().getPlayer();
    }

    public void disablePremium(String playerId, DataRetriever<PlayerModel> retriever) {
        provideRepository().makePremium(0, playerId, retriever);
    }

    public void cachePlayer(PlayerModel player) {
        QuizProvider.provideIoManager().savePlayer(player);
    }
}
