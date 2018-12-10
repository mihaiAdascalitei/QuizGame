package com.dasteam.quiz.quizgame.gui.feedback;

import com.dasteam.quiz.quizgame.gui.feedback.status.FeedbackResponseStatus;

public interface FeedbackCallback {
    void onFeedbackAttempt(FeedbackResponseStatus status);
}
