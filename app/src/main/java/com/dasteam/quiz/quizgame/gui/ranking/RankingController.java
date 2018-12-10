package com.dasteam.quiz.quizgame.gui.ranking;

import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;

import java.util.List;

import static com.dasteam.quiz.quizgame.provider.QuizProvider.provideRepository;

public class RankingController {

    public void getRankingPlayers(DataRetriever<List<PlayerModel>> retriever) {
        provideRepository().getRankingPlayers(retriever);
    }
}
