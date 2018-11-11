package com.dasteam.quiz.quizgame.gui.register;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;

public class RegisterActivity extends BaseActivity {

    private Toolbar toolbar;
    private RegisterController registerController;

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_register);
        configureToolbar();
    }

    @Override
    protected void attachViews() {
        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    protected void attachController() {
        registerController = new RegisterController();
    }

    @Override
    protected void setListeners() {

    }

    private void configureToolbar() {
        toolbar.setTitle(getString(R.string.register));
        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.register_menu, menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_close);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_save:
                save();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void save() {

    }
}
