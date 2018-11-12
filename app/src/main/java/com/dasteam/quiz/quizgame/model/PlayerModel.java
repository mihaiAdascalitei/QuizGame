package com.dasteam.quiz.quizgame.model;

import com.google.gson.annotations.SerializedName;

public class PlayerModel {
    @SerializedName("id")
    private String id;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }
}
