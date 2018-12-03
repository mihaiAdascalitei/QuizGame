package com.dasteam.quiz.quizgame.gui.powerups.buypowerups;

import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.model.powerups.PowerUpsModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;
import com.dasteam.quiz.quizgame.provider.QuizProvider;

import java.util.List;

public class BuyPowerUpsController {

    public void getPowerUps(DataRetriever<List<PowerUpsModel>> retriever) {
        QuizProvider.provideRepository().getPowerUps(retriever);
    }

    public void buyPowerUps(String playerId, String powerId, String powerCount, DataRetriever<List<PowerUpsModel>> retriever) {
        QuizProvider.provideRepository().buyPowerUps(playerId, powerId, powerCount, retriever);
    }

    public void updateCredit(String playerId, String credit, DataRetriever<PlayerModel> retriever) {
        QuizProvider.provideRepository().updateCredit(playerId, credit, retriever);
    }

    public void cachePlayer(PlayerModel player) {
        QuizProvider.provideIoManager().savePlayer(player);
    }
}
