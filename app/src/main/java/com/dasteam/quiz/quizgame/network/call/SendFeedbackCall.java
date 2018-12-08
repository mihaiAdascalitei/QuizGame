package com.dasteam.quiz.quizgame.network.call;

import com.dasteam.quiz.quizgame.model.feedback.FeedbackModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SendFeedbackCall {
    @POST("/sendFeedback")
    Call<String> sendFeedback(@Body FeedbackModel feedback);
}
