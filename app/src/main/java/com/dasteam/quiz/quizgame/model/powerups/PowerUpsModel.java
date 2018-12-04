package com.dasteam.quiz.quizgame.model.powerups;

import com.google.gson.annotations.SerializedName;

public class PowerUpsModel {


    @SerializedName("power_name")
    private String powerName;

    @SerializedName("power_icon_key")
    private String powerIconKey;

    @SerializedName("power_price")
    private String powerPrice;

    @SerializedName("power_description")
    private String powerDescription;

    @SerializedName("power_count")
    private String powerCount;

    @SerializedName("player_id")
    private String playerId;

    @SerializedName("power_id")
    private String powerId;


    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }

    public String getPowerIconKey() {
        return powerIconKey;
    }

    public void setPowerIconKey(String powerIconKey) {
        this.powerIconKey = powerIconKey;
    }

    public String getPowerPrice() {
        return powerPrice;
    }

    public void setPowerPrice(String powerPrice) {
        this.powerPrice = powerPrice;
    }

    public String getPowerDescription() {
        return powerDescription;
    }

    public void setPowerDescription(String powerDescription) {
        this.powerDescription = powerDescription;
    }

    public String getPowerCount() {
        return powerCount;
    }

    public void setPowerCount(String powerCount) {
        this.powerCount = powerCount;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPowerId() {
        return powerId;
    }

    public void setPowerId(String powerId) {
        this.powerId = powerId;
    }
}
