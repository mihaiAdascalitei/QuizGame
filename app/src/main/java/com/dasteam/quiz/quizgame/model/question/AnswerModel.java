package com.dasteam.quiz.quizgame.model.question;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AnswerModel implements Serializable {

    @SerializedName("answer_id")
    private String id;

    @SerializedName("answer")
    private String name;

    @SerializedName("is_correct")
    private int isCorrect;

    private boolean isSelected;

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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
