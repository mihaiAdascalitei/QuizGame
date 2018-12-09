package com.dasteam.quiz.quizgame.gui.feedback.history;

import com.dasteam.quiz.quizgame.model.feedback.FeedbackModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;

import java.util.List;

import static com.dasteam.quiz.quizgame.provider.QuizProvider.provideRepository;

public class FeedbackHistoryController {

    public void getPlayerFeedback(String playerId, DataRetriever<List<FeedbackModel>> retriever) {
        provideRepository().getPlayerFeedback(playerId, retriever);
    }

    public void removeFeedback(String feedbackId, String playerId, DataRetriever<List<FeedbackModel>> retriever) {
        provideRepository().removeFeedback(feedbackId, playerId, retriever);
    }
}
