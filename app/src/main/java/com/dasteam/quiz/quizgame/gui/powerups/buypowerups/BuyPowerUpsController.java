package com.dasteam.quiz.quizgame.gui.powerups.buypowerups;

import com.dasteam.quiz.quizgame.model.powerups.PowerUpsModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;
import com.dasteam.quiz.quizgame.provider.QuizProvider;

import java.util.List;

public class BuyPowerUpsController {

    public void getPowerUps(DataRetriever<List<PowerUpsModel>> retriever) {
        QuizProvider.provideRepository().getPowerUps(retriever);
    }
}
