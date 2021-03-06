package com.dasteam.quiz.quizgame.gui.mainscreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;
import com.dasteam.quiz.quizgame.gui.lobby.LobbyActivity;
import com.dasteam.quiz.quizgame.gui.mainscreen.animate.AnimatorTouchListener;
import com.dasteam.quiz.quizgame.gui.powerups.PowerUpsActivity;
import com.dasteam.quiz.quizgame.gui.premium.PremiumAccountActivity;
import com.dasteam.quiz.quizgame.gui.profile.ProfileActivity;
import com.dasteam.quiz.quizgame.gui.quiz.QuizActivity;
import com.dasteam.quiz.quizgame.gui.ranking.RankingActivity;
import com.dasteam.quiz.quizgame.gui.settings.SettingsActivity;
import com.dasteam.quiz.quizgame.model.player.PlayerModel;

public class MainScreenActivity extends BaseActivity {

    private MainScreenController mainController;
    private PlayerModel player;

    private RelativeLayout rlSingleplayer;
    private RelativeLayout rlMultiplayer;
    private TextView tvRanking;
    private TextView tvAds;
    private TextView tvPowerUps;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_screen_menu, menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_user);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                openProfileScreen();
                return true;
            case R.id.menu_item_premium:
                openPremiumAccount();
                return true;
            case R.id.menu_item_settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_main_screen);
        fetchCachedData();
        configureToolbar();
    }


    @Override
    protected void attachViews() {
        rlMultiplayer = findViewById(R.id.rl_multiplayer);
        rlSingleplayer = findViewById(R.id.rl_singleplayer);
        tvRanking = findViewById(R.id.tv_ranking);
        tvAds = findViewById(R.id.tv_ads);
        tvPowerUps = findViewById(R.id.tv_power_up);
    }


    @Override
    protected void attachController() {
        mainController = new MainScreenController();
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void setListeners() {
        rlSingleplayer.setOnTouchListener(new AnimatorTouchListener(this::openQuizScreen));
        rlMultiplayer.setOnTouchListener(new AnimatorTouchListener(this::openLobbyScreen));
        tvRanking.setOnClickListener(v -> openRankings());
        tvAds.setOnClickListener(v -> openPremiumAccount());
        tvPowerUps.setOnClickListener(v -> openPowerUps());
    }


    private void fetchCachedData() {
        player = mainController.getPlayer();
    }


    @Override
    public void onResume() {
        super.onResume();
        fetchCachedData();
    }


    private void configureToolbar() {
        String title = getString(R.string.main_screen) + " " + player.getUsername();
        configureToolbar(title);
    }

    private void openProfileScreen() {
        startActivity(new Intent(this, ProfileActivity.class));
    }

    private void openLobbyScreen() {
        startActivity(new Intent(this, LobbyActivity.class));
    }

    private void openRankings() {
        startActivity(new Intent(this, RankingActivity.class));
    }

    private void openPremiumAccount() {
        startActivity(new Intent(this, PremiumAccountActivity.class)
                .putExtra(PremiumAccountActivity.PREMIUM_PLAYER, player));
    }

    private void openPowerUps() {
        startActivity(new Intent(this, PowerUpsActivity.class).putExtra(PowerUpsActivity.POWER_UPS_PLAYER, player));
    }

    private void openSettings() {
        startActivity(new Intent(this, SettingsActivity.class).putExtra(SettingsActivity.SETTINGS_PLAYER, player));
    }

    private void openQuizScreen() {
        startActivity(new Intent(this, QuizActivity.class));
    }

}
