package com.dasteam.quiz.quizgame.network.call;

import com.dasteam.quiz.quizgame.model.feedback.FeedbackModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public interface RemoveFeedbackCall {
    @DELETE("/removeFeedback/{feedbackId}/{playerId}")
    Call<List<FeedbackModel>> removeFeedback(@Path("feedbackId") String feedbackId,
                                             @Path("playerId") String playerId);
}
