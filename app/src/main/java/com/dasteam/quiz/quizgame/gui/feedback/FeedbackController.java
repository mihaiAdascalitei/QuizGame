package com.dasteam.quiz.quizgame.gui.feedback;

import com.dasteam.quiz.quizgame.gui.feedback.status.FeedbackResponseStatus;
import com.dasteam.quiz.quizgame.network.DataRetriever;
import com.dasteam.quiz.quizgame.network.RetrofitRepository;

import static com.dasteam.quiz.quizgame.utils.QuizUtils.isEmpty;

public class FeedbackController {

    private RetrofitRepository repository;

    public FeedbackController() {
        repository = new RetrofitRepository();
    }

    public void validateData(String category, String description, FeedbackCallback callback) {
        if (category == null) {
            callback.onFeedbackAttempt(FeedbackResponseStatus.CATEGORY_ERROR);
        } else if (isEmpty(description)) {
            callback.onFeedbackAttempt(FeedbackResponseStatus.EMPTY_DESCRIPTION);
        } else if (description.length() < 10) {
            callback.onFeedbackAttempt(FeedbackResponseStatus.LENGTH_ERROR);
        } else {
            callback.onFeedbackAttempt(FeedbackResponseStatus.SUCCESS);
        }
    }

    public void sendFeedback(String playerId, String category, String description, DataRetriever<String> retriever) {
        repository.sendFeedback(playerId, category, description, retriever);
    }
}
