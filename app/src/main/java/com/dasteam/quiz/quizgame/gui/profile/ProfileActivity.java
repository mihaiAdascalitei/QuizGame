package com.dasteam.quiz.quizgame.gui.profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;
import com.dasteam.quiz.quizgame.gui.login.LoginActivity;
import com.dasteam.quiz.quizgame.gui.profile.background.LogoutTask;
import com.dasteam.quiz.quizgame.model.PlayerModel;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

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
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), IMAGE_PICK);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_PICK) {
            if (resultCode == RESULT_OK) {

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
        Glide.with(this)
                .load(profileImage)
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
