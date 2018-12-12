package com.dasteam.quiz.quizgame.network.call.feedback;

import com.dasteam.quiz.quizgame.model.feedback.FeedbackModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FeedbackCall {
    @GET("/getFeedback/{id}")
    Call<List<FeedbackModel>> getPlayerFeedbackList(@Path("id") String id);


    @DELETE("/removeFeedback/{feedbackId}/{playerId}")
    Call<List<FeedbackModel>> removeFeedback(@Path("feedbackId") String feedbackId,
                                             @Path("playerId") String playerId);

    @POST("/sendFeedback")
    Call<String> sendFeedback(@Body FeedbackModel feedback);
}

