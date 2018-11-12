package com.dasteam.quiz.quizgame.model;

import com.google.gson.annotations.SerializedName;

public class PlayerModel {
    @SerializedName("id")
    private String id;

    @SerializedName("username")
    private String username;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
