package com.dasteam.quiz.quizgame.gui.quiz.endgame;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;
import com.dasteam.quiz.quizgame.gui.quiz.base.BaseFragment;
import com.dasteam.quiz.quizgame.gui.quiz.endgame.adapter.EndGameAlsoAdapter;
import com.dasteam.quiz.quizgame.gui.quiz.endgame.adapter.EndGameAnswerAdapter;
import com.dasteam.quiz.quizgame.model.question.QuestionModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class EndGameFragment extends BaseFragment {
    private TextView tvExpand;
    private RecyclerView rvAnswers;
    private RecyclerView rvAlso;
    private TextView tvHome;
    private EndGameAlsoAdapter alsoAdapter;
    private EndGameAnswerAdapter answerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_end_game, container, false);
        initViews(view);
        initAdapter();
        setListeners();
        return view;
    }

    private void initViews(View view) {
        tvExpand = view.findViewById(R.id.tv_end_game_check_answers);
        rvAlso = view.findViewById(R.id.rv_end_game_also);
        rvAnswers = view.findViewById(R.id.rv_end_game_answers);
        tvHome = view.findViewById(R.id.tv_end_game_home);
    }

    private void initAdapter() {
        answerAdapter = new EndGameAnswerAdapter();
        alsoAdapter = new EndGameAlsoAdapter();
        rvAnswers.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rvAnswers.setNestedScrollingEnabled(false);
        rvAlso.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvAnswers.setAdapter(answerAdapter);
        rvAlso.setAdapter(alsoAdapter);
        initAlsoAdapterData();
        initAnswerAdapterData();
    }

    private void initAlsoAdapterData() {
        String[] data = Objects.requireNonNull(getContext()).getResources().getStringArray(R.array.end_game_also_options);
        alsoAdapter.setData(Arrays.asList(data));
    }

    private void initAnswerAdapterData() {
        List<QuestionModel> dummy = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            dummy.add(new QuestionModel());
        }

        answerAdapter.setData(dummy);
    }

    private void setListeners() {
        tvHome.setOnClickListener(v -> goHome());
        tvExpand.setOnClickListener(v -> expand());
    }

    private void goHome() {
        ((BaseActivity) Objects.requireNonNull(getContext())).finish();
    }

    private void expand() {
        boolean isExpanded = rvAnswers.getVisibility() == View.VISIBLE;
        rvAnswers.setVisibility(isExpanded ? View.GONE : View.VISIBLE);
        tvExpand.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                isExpanded ? R.drawable.ic_expand_more_green_24dp : R.drawable.ic_expand_less_green_24dp, 0);
    }

}
