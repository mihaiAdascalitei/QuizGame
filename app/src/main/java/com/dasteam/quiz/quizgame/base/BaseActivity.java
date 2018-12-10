package com.dasteam.quiz.quizgame.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.custom.LoadingDialog;

public abstract class BaseActivity extends AppCompatActivity {

    private static final long DIALOG_TIME = 1500;
    private LoadingDialog loading;

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

    protected void onCreate(@Nullable Bundle savedInstanceState, int layout) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(layout);
        attachViews();
        attachController();
        setListeners();
    }

    protected abstract void attachViews();

    protected abstract void attachController();

    protected abstract void setListeners();

    protected void configureToolbar(String title) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
    }


    protected void showAlert(String message) {
        new AlertDialog
                .Builder(this, R.style.AlertDialogStyle)
                .setTitle(getString(R.string.alert_title))
                .setPositiveButton(getString(R.string.button_ok), null)
                .setMessage(message)
                .create()
                .show();
    }

    protected void showDelayedAlert(String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogStyle)
                .setMessage(message);
        final AlertDialog dialog = builder.create();
        dialog.show();
        new Handler().postDelayed(() -> {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }, DIALOG_TIME);
    }


    protected void showLoading(boolean show) {
        if (show) {
            loading.show();
        } else {
            loading.cancel();
        }
    }

    protected void showSnackBar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    private void init() {
        loading = new LoadingDialog(this);
    }


}
