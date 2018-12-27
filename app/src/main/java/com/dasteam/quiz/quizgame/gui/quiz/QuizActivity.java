package com.dasteam.quiz.quizgame.gui.quiz;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;
import com.dasteam.quiz.quizgame.gui.quiz.announcement.AnnouncementFragment;

public class QuizActivity extends BaseActivity {

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_quiz);
        configureToolbar(getString(R.string.game_selected));
        startFragment(new AnnouncementFragment());
    }

    @Override
    protected void attachViews() {

    }

    @Override
    protected void attachController() {

    }

    @Override
    protected void setListeners() {

    }

    private void startFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.quiz_container, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        //nothing
    }

    public void showSurrenderAlert() {
        new AlertDialog.Builder(this, R.style.AlertDialogStyle)
                .setTitle(getString(R.string.surrender))
                .setMessage(getString(R.string.surrender_message))
                .setPositiveButton(getString(R.string.button_ok), (dialog, which) -> {
                    QuizActivity.this.finish();
                })
                .setNegativeButton(getString(R.string.cancel), null)
                .create()
                .show();
    }
}
