package com.dasteam.quiz.quizgame.gui.terms;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;

public class TermsAndConditionsActivity extends BaseActivity {
    private LinearLayout llTerms;

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_terms_and_conditions);
        configureToolbar(getString(R.string.settings_terms));
    }

    @Override
    protected void attachViews() {
        llTerms = findViewById(R.id.ll_terms);
    }

    @Override
    protected void attachController() {

    }

    @Override
    protected void setListeners() {
        llTerms.setOnClickListener(v ->showSnackBar(llTerms, getString(R.string.accepted)));
    }
}
