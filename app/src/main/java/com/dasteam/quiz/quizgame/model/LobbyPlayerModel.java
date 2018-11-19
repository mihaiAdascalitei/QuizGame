package com.dasteam.quiz.quizgame.model;

import com.google.gson.annotations.SerializedName;

public class LobbyPlayerModel {
    @SerializedName("username")
    private String username;

    @SerializedName("rank")
    private int rank;

    @SerializedName("id")
    private String id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
