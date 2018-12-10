
package com.dasteam.quiz.quizgame.gui.about;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;
import com.dasteam.quiz.quizgame.gui.feedback.FeedbackActivity;
import com.dasteam.quiz.quizgame.gui.powerups.PowerUpsActivity;
import com.dasteam.quiz.quizgame.gui.profile.ProfileActivity;
import com.dasteam.quiz.quizgame.gui.ranking.RankingActivity;
import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.model.powerups.PowerUpsModel;

public class AboutActivity extends BaseActivity {

    public static final String ABOUT_PLAYER = "ABOUT_PLAYER";
    private TextView tvFeedback;
    private TextView tvProfile;
    private TextView tvPowerUps;
    private TextView tvRanking;
    private PlayerModel player;

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_about);
        configureToolbar(getString(R.string.settings_about));
        getExtraData();
    }

    @Override
    protected void attachViews() {
        tvFeedback = findViewById(R.id.tv_about_feedback);
        tvProfile = findViewById(R.id.tv_about_profile);
        tvPowerUps = findViewById(R.id.tv_about_powerups);
        tvRanking = findViewById(R.id.tv_about_rankings);
    }

    @Override
    protected void attachController() {

    }

    @Override
    protected void setListeners() {
        tvFeedback.setOnClickListener(v -> openFeedback());
        tvProfile.setOnClickListener(v -> openProfile());
        tvPowerUps.setOnClickListener(v -> openPowerUps());
        tvRanking.setOnClickListener(v -> openRanking());
    }

    private void getExtraData() {
        player = (PlayerModel) getIntent().getSerializableExtra(ABOUT_PLAYER);
    }

    private void openFeedback() {
        startActivity(new Intent(this, FeedbackActivity.class).putExtra(FeedbackActivity.FEEDBACK_PLAYER, player));
    }

    private void openProfile() {
        startActivity(new Intent(this, ProfileActivity.class));
    }

    private void openPowerUps() {
        startActivity(new Intent(this, PowerUpsActivity.class).putExtra(PowerUpsActivity.POWER_UPS_PLAYER, player));
    }

    private void openRanking() {
        startActivity(new Intent(this, RankingActivity.class));
    }
}
