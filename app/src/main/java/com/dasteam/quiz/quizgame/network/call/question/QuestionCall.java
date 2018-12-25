package com.dasteam.quiz.quizgame.network.call.question;

import com.dasteam.quiz.quizgame.model.question.QuestionModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuestionCall {
    @GET("/questions")
    Call<List<QuestionModel>> getQuestions();
}
