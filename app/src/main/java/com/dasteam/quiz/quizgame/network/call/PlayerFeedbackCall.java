package com.dasteam.quiz.quizgame.network.call;

import com.dasteam.quiz.quizgame.model.feedback.FeedbackModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlayerFeedbackCall {
    @GET("/getFeedback/{id}")
    Call<List<FeedbackModel>> getPlayerFeedback(@Path("id") String id);
}
