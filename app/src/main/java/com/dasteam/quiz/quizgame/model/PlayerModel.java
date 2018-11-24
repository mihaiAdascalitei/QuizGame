package com.dasteam.quiz.quizgame.model;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlayerModel implements Serializable {

    public static final int RANK_FIRST = 1;
    public static final int RANK_SECOND = 2;
    public static final int RANK_THIRD = 3;

    @SerializedName("id")
    private String id;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;


    private String profileImage;

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

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
