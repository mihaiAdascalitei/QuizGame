package com.dasteam.quiz.quizgame.model.question;

import com.google.gson.annotations.SerializedName;

public class AnswerModel {

    @SerializedName("answer_id")
    private String id;

    @SerializedName("answer")
    private String name;

    @SerializedName("is_correct")
    private int isCorrect;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCorrect() {
        return isCorrect == 1;
    }
}
