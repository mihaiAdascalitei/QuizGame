package com.dasteam.quiz.quizgame.model.powerups;

import com.google.gson.annotations.SerializedName;

public class PowerUpsModel {

    @SerializedName("id")
    private String id;

    @SerializedName("power_name")
    private String powerName;

    @SerializedName("power_icon_key")
    private String powerIconKey;

    @SerializedName("power_price")
    private String powerPrice;

    @SerializedName("power_description")
    private String powerDescription;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
