package com.dasteam.quiz.quizgame.model.feedback;

import com.google.gson.annotations.SerializedName;

public class FeedbackModel {

    @SerializedName("feedback_id")
    private String feedbackId;

    @SerializedName("player_id")
    private String playerId;

    @SerializedName("category")
    private String category;

    @SerializedName("description")
    private String description;

    @SerializedName("date_created")
    private String dateCreated;

    public FeedbackModel() {

    }

    public FeedbackModel(String playerId, String category, String description, String dateCreated) {
        this.playerId = playerId;
        this.category = category;
        this.description = description;
        this.dateCreated = dateCreated;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
