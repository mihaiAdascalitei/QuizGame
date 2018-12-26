package com.dasteam.quiz.quizgame.gui.quiz.nolives;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;
import com.dasteam.quiz.quizgame.gui.quiz.base.BaseFragment;

import java.util.Objects;

public class NoLivesFragment extends BaseFragment {
    private TextView tvHome;
    private TextView tvRetry;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_no_lives, container, false);
        initViews(view);
        setListeners();
        return view;
    }

    private void initViews(View view) {
        tvHome = view.findViewById(R.id.tv_lost_game_home);
        tvRetry = view.findViewById(R.id.tv_lost_game_try_again);
    }

    private void setListeners() {
        tvHome.setOnClickListener(v -> ((BaseActivity) Objects.requireNonNull(getContext())).finish());
        tvRetry.setOnClickListener(v -> fragmentNavigator().pop());
    }
}
