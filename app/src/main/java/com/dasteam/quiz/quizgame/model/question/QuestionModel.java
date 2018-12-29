package com.dasteam.quiz.quizgame.model.question;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class QuestionModel implements Serializable {

    @SerializedName("question_id")
    private String questionId;

    @SerializedName("question")
    private String question;

    @SerializedName("answers")
    private List<AnswerModel> answers;

    @SerializedName("range_answer")
    private String rangeAnswer;


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<AnswerModel> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerModel> answers) {
        this.answers = answers;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getRangeAnswer() {
        return rangeAnswer;
    }

    public void setRangeAnswer(String rangeAnswer) {
        this.rangeAnswer = rangeAnswer;
    }

}
