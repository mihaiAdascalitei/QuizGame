package com.dasteam.quiz.quizgame.gui.quiz.endgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;
import com.dasteam.quiz.quizgame.gui.profile.ProfileActivity;
import com.dasteam.quiz.quizgame.gui.quiz.QuizActivity;
import com.dasteam.quiz.quizgame.gui.quiz.base.BaseFragment;
import com.dasteam.quiz.quizgame.gui.quiz.endgame.adapter.EndGameAlsoAdapter;
import com.dasteam.quiz.quizgame.gui.quiz.endgame.adapter.EndGameAnswerAdapter;
import com.dasteam.quiz.quizgame.gui.ranking.RankingActivity;
import com.dasteam.quiz.quizgame.model.question.QuestionModel;
import com.dasteam.quiz.quizgame.utils.QuizUtils;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class EndGameFragment extends BaseFragment {
    public static final String END_GAME_LIST = "END_GAME_LIST";
    public static final String END_GAME_POINTS = "END_GAME_POINTS";

    private TextView tvExpand;
    private RecyclerView rvAnswers;
    private RecyclerView rvAlso;
    private TextView tvHome;
    private TextView tvPoints;
    private EndGameAlsoAdapter alsoAdapter;
    private EndGameAnswerAdapter answerAdapter;
    private List<QuestionModel> questions;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_end_game, container, false);
        initViews(view);
        getQuestionsFromArgs();
        initAdapter();
        setListeners();
        return view;
    }

    private void initViews(View view) {
        tvExpand = view.findViewById(R.id.tv_end_game_check_answers);
        rvAlso = view.findViewById(R.id.rv_end_game_also);
        rvAnswers = view.findViewById(R.id.rv_end_game_answers);
        tvHome = view.findViewById(R.id.tv_end_game_home);
        tvPoints = view.findViewById(R.id.tv_end_game_points);
    }

    private void getQuestionsFromArgs() {
        Bundle args = getArguments();
        if (args != null) {
            tvPoints.setText(args.getString(END_GAME_POINTS));
            Type type = new TypeToken<List<QuestionModel>>() {
            }.getType();
            questions = QuizUtils.getArrayFromJsonString(args.getString(END_GAME_LIST), type);
        }
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
        alsoAdapter.setCallback(this::handleAlsoOptions);
    }

    private void handleAlsoOptions(int pos) {
        switch (pos) {
            case 0:
                startNewActivity(RankingActivity.class);
                break;
            case 1:
                fragmentNavigator().pop();
                break;
            case 2:
                startNewActivity(ProfileActivity.class);
                break;
            default:
                break;
        }
    }

    private void initAnswerAdapterData() {
        answerAdapter.setData(questions);
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

    private void startNewActivity(Class clasz) {
        ((QuizActivity) Objects.requireNonNull(getContext())).finish();
        Objects.requireNonNull(getContext()).startActivity(new Intent(getContext(), clasz));
    }

}
