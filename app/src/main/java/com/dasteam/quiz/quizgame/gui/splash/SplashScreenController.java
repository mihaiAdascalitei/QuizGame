package com.dasteam.quiz.quizgame.gui.splash;

import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;
import com.dasteam.quiz.quizgame.network.RetrofitRepository;
import com.dasteam.quiz.quizgame.provider.QuizProvider;

public class SplashScreenController {
    private RetrofitRepository repository;

    public SplashScreenController() {
        repository = new RetrofitRepository();
    }

    public PlayerModel getPlayer() {
        return QuizProvider.provideIoManager().getPlayer();
    }

    public void disablePremium(String playerId, DataRetriever<PlayerModel> retriever) {
        repository.makePremium(0, playerId, retriever);
    }

    public void cachePlayer(PlayerModel player) {
        QuizProvider.provideIoManager().savePlayer(player);
    }
}
