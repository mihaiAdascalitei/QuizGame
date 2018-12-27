package com.dasteam.quiz.quizgame.model.player;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public enum Ranking implements Serializable {
    @SerializedName("0") APPRENTICE,
    @SerializedName("1") HANDY,
    @SerializedName("2") WARRIOR,
    @SerializedName("3") TITAN
}
