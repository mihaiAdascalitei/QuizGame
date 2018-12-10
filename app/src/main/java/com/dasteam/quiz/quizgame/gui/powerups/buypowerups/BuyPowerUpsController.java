package com.dasteam.quiz.quizgame.gui.powerups.buypowerups;

import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.model.powerups.PowerUpsModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;
import com.dasteam.quiz.quizgame.provider.QuizProvider;

import java.util.List;

import static com.dasteam.quiz.quizgame.provider.QuizProvider.provideRepository;

public class BuyPowerUpsController {

    public void getPowerUps(DataRetriever<List<PowerUpsModel>> retriever) {
        provideRepository().getPowerUps(retriever);
    }

    public void buyPowerUps(String playerId, String powerId, String powerCount, DataRetriever<List<PowerUpsModel>> retriever) {
        provideRepository().buyPowerUps(playerId, powerId, powerCount, retriever);
    }

    public void updateCredit(String playerId, String credit, DataRetriever<PlayerModel> retriever) {
        provideRepository().updateCredit(playerId, credit, retriever);
    }

    public void cachePlayer(PlayerModel player) {
        QuizProvider.provideIoManager().savePlayer(player);
    }

    public void checkPlayerPower(String playerId, String powerId, DataRetriever<List<PowerUpsModel>> retriever) {
        provideRepository().checkPlayerPowerUp(playerId, powerId, retriever);
    }
}
