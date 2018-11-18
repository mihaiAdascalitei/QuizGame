package com.dasteam.quiz.quizgame.gui.mainscreen;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;
import com.dasteam.quiz.quizgame.gui.mainscreen.animate.AnimatorTouchListener;
import com.dasteam.quiz.quizgame.gui.profile.ProfileActivity;
import com.dasteam.quiz.quizgame.model.PlayerModel;
import com.dasteam.quiz.quizgame.utils.Animator;

import static com.dasteam.quiz.quizgame.utils.Animator.animate;
import static com.dasteam.quiz.quizgame.utils.Animator.rotate;

public class MainScreenActivity extends BaseActivity {

    public static String MAIN_SCREEN_PLAYER = "MAIN_SCREEN_PLAYER";
    private MainScreenController mainController;
    private PlayerModel player;

    private RelativeLayout rlSingleplayer;
    private RelativeLayout rlMultiplayer;


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

                return true;
            case R.id.menu_item_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_main_screen);
        getExtraData();
        configureToolbar();
    }


    @Override
    protected void attachViews() {
        rlMultiplayer = findViewById(R.id.rl_multiplayer);
        rlSingleplayer = findViewById(R.id.rl_singleplayer);
    }

    @Override
    protected void attachController() {
        mainController = new MainScreenController();

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void setListeners() {
        rlSingleplayer.setOnTouchListener(new AnimatorTouchListener());
        rlMultiplayer.setOnTouchListener(new AnimatorTouchListener());
    }

    private void getExtraData() {
        if (getIntent().hasExtra(MAIN_SCREEN_PLAYER)) {
            player = (PlayerModel) getIntent().getSerializableExtra(MAIN_SCREEN_PLAYER);
        }
    }

    private void configureToolbar() {
        String title = getString(R.string.main_screen) + " " + player.getUsername();
        configureToolbar(title);
    }

    private void openProfileScreen() {
        startActivity(new Intent(this, ProfileActivity.class));
    }

}
