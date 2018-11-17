package com.dasteam.quiz.quizgame.gui.profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;
import com.dasteam.quiz.quizgame.gui.login.LoginActivity;
import com.dasteam.quiz.quizgame.gui.profile.background.LogoutCallback;
import com.dasteam.quiz.quizgame.gui.profile.background.LogoutTask;
import com.dasteam.quiz.quizgame.model.PlayerModel;
import com.dasteam.quiz.quizgame.provider.QuizProvider;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;

public class ProfileActivity extends BaseActivity {
    private static final int IMAGE_PICK = 10;

    private ProfileController profileController;
    private PlayerModel player;

    private Button btnLogout;
    private ImageView ivProfileIcon;
    private TextView tvUsername;


    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_profile);
        configureToolbar(getString(R.string.profile));
        getExtraData();
    }

    @Override
    protected void attachViews() {
        btnLogout = findViewById(R.id.btn_logout);
        ivProfileIcon = findViewById(R.id.iv_profile_icon);
        tvUsername = findViewById(R.id.tv_profile_name);
    }

    @Override
    protected void attachController() {
        profileController = new ProfileController();
    }

    @Override
    protected void setListeners() {
        btnLogout.setOnClickListener(v -> logout());
        ivProfileIcon.setOnClickListener(v -> loadImage());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_keyboard_backspace);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void getExtraData() {
        profileController.getPlayer(playerModel -> {
            player = playerModel;
            updateFields();
        });
    }

    private void logout() {
        showDialog(true);
        new LogoutTask(() -> {
            showDialog(false);
            openLoginScreen();
        }).execute();
    }

    private void openLoginScreen() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void loadImage() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), IMAGE_PICK);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_PICK) {

                if (data != null) {
                    Uri profileImage = data.getData();
                    loadImageIntoView(profileImage);
                    player.setProfileImage(profileImage.toString());
                    profileController.updatePlayer(player);
                }

            }
        }
    }

    private void loadImageIntoView(Uri profileImage) {

        Picasso.get().load(profileImage).noPlaceholder().centerCrop().fit()
                .into(ivProfileIcon);
    }

    private void updateFields() {
        tvUsername.setText(player.getUsername());

        String profileImage = player.getProfileImage();
        if (profileImage != null) {
            loadImageIntoView(Uri.parse(profileImage));
        }
    }
}
