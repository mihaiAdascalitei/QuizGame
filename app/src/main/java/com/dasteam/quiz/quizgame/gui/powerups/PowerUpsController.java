package com.dasteam.quiz.quizgame.gui.powerups;

import com.dasteam.quiz.quizgame.model.powerups.PowerUpsModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;
import com.dasteam.quiz.quizgame.provider.QuizProvider;

import java.util.List;

public class PowerUpsController {

    public void getPlayerPowerUps(String id, DataRetriever<List<PowerUpsModel>> retriever) {
        QuizProvider.provideRepository().getPlayerPowerUps(id, retriever);
    }
}
