package com.dasteam.quiz.quizgame.model.player;

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

    @SerializedName("has_premium")
    private int hasPremium;

    @SerializedName("credit")
    private String credit;

    @SerializedName("premium_date_activated")
    private String premiumDateActivated;


    private String profileImage;

    public PlayerModel() {
    }

    public PlayerModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

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

    public boolean hasPremium() {
        return hasPremium == 1;
    }

    public void setHasPremium(int hasPremium) {
        this.hasPremium = hasPremium;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getPremiumDateActivated() {
        return premiumDateActivated;
    }

    public void setPremiumDateActivated(String premiumDateActivated) {
        this.premiumDateActivated = premiumDateActivated;
    }
}
