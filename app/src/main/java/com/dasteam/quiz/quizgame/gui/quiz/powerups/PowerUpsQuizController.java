package com.dasteam.quiz.quizgame.gui.quiz.powerups;

import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.model.powerups.PowerUpsModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;

import java.util.List;

import static com.dasteam.quiz.quizgame.provider.QuizProvider.provideIoManager;
import static com.dasteam.quiz.quizgame.provider.QuizProvider.provideRepository;

public class PowerUpsQuizController {

    public PlayerModel getPlayer() {
        return provideIoManager().getPlayer();
    }

    public void getPlayerPowerUps(String playerId, DataRetriever<List<PowerUpsModel>> retriever) {
        provideRepository().getPlayerPowerUps(playerId, retriever);
    }

    public void usePowerUp(String playerId, String powerId, String powerCount, DataRetriever<List<PowerUpsModel>> retriever) {
        provideRepository().sellPowerUps(playerId, powerId, powerCount, retriever);
    }
}
