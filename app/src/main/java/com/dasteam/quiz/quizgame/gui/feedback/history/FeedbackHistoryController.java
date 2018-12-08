package com.dasteam.quiz.quizgame.gui.feedback.history;

import com.dasteam.quiz.quizgame.model.feedback.FeedbackModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;
import com.dasteam.quiz.quizgame.network.RetrofitRepository;

import java.util.List;

public class FeedbackHistoryController {
    private RetrofitRepository repository;

    public FeedbackHistoryController() {
        repository = new RetrofitRepository();
    }

    public void getPlayerFeedback(String playerId, DataRetriever<List<FeedbackModel>> retriever) {
        repository.getPlayerFeedback(playerId, retriever);
    }

    public void removeFeedback(String feedbackId, String playerId, DataRetriever<List<FeedbackModel>> retriever) {
        repository.removeFeedback(feedbackId, playerId, retriever);
    }
}
