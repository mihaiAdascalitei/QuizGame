package com.dasteam.quiz.quizgame.gui.quiz.options;

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
import com.dasteam.quiz.quizgame.gui.quiz.base.BaseFragment;
import com.dasteam.quiz.quizgame.gui.quiz.endgame.EndGameFragment;
import com.dasteam.quiz.quizgame.gui.quiz.nolives.NoLivesFragment;
import com.dasteam.quiz.quizgame.gui.quiz.options.adpater.VariantsAdapter;
import com.dasteam.quiz.quizgame.model.question.AnswerModel;
import com.dasteam.quiz.quizgame.model.question.QuestionModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;

import java.util.List;
import java.util.Objects;

import static com.dasteam.quiz.quizgame.utils.QuizUtils.integer;
import static com.dasteam.quiz.quizgame.utils.QuizUtils.string;

public class VariantsFragment extends BaseFragment {

    private static int CURRENT_QUESTION_INDEX = 0;
    private static final int MAX_INITIAL_LIVES = 2;

    private RecyclerView rvOptions;
    private TextView tvQuestion;
    private TextView tvSubmit;
    private TextView tvPoints;
    private TextView tvLives;

    private VariantsAdapter adapter;
    private VariantsController controller;
    private List<QuestionModel> questions;
    private AnswerModel currentAnswer = null;
    private int wrongAnswerCounter = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_options, container, false);
        attachViews(view);
        initAdapter();
        getQuestions();
        setListeners();
        resetCounters();
        return view;
    }


    private void attachViews(View view) {
        rvOptions = view.findViewById(R.id.rv_options);
        tvQuestion = view.findViewById(R.id.tv_options_question);
        tvSubmit = view.findViewById(R.id.tv_option_submit);
        tvPoints = view.findViewById(R.id.tv_options_points);
        tvLives = view.findViewById(R.id.tv_options_lives);
        controller = new VariantsController();
    }

    private void initAdapter() {
        adapter = new VariantsAdapter(this.getContext());
        rvOptions.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rvOptions.setAdapter(adapter);
        adapter.setCallback(answer -> currentAnswer = answer);
    }

    private void setListeners() {
        tvSubmit.setOnClickListener(v -> submit());
    }

    private void updateQuestions() {
        tvQuestion.setText(questions.get(CURRENT_QUESTION_INDEX).getQuestion());
        adapter.setData(questions.get(CURRENT_QUESTION_INDEX).getAnswers());

    }

    private void showLoading(boolean value) {
        ((BaseActivity) Objects.requireNonNull(getActivity())).showLoading(value);
    }

    private void getQuestions() {
        showLoading(true);
        controller.getQuestions(new DataRetriever<List<QuestionModel>>() {
            @Override
            public void onDataRetrieved(List<QuestionModel> data) {
                showLoading(false);
                questions = data;
                updateQuestions();
            }

            @Override
            public void onDataFailed(String message, int code) {
                showLoading(false);
            }
        });
    }

    private void submit() {
        checkCountersAndPoints();

        if (wrongAnswerCounter == MAX_INITIAL_LIVES) {
            fragmentNavigator().replace(new NoLivesFragment());
        } else {
            CURRENT_QUESTION_INDEX++;
            if (CURRENT_QUESTION_INDEX == questions.size()) {
                fragmentNavigator().replace(new EndGameFragment());
            } else {
                updateQuestions();
                resetAnswerData();
            }
        }
    }


    private void calculatePoints() {
        int points = integer(tvPoints.getText().toString());
        points += points * 4.5 + 1.5 * 3;
        tvPoints.setText(string(points));
    }

    private void decrementLives() {
        int lives = integer(tvLives.getText().toString());
        lives--;
        tvLives.setText(string(lives));
    }

    private void checkCountersAndPoints() {
        if (currentAnswer != null) {
            if (!currentAnswer.isCorrect()) {
                wrongAnswerCounter++;
                decrementLives();
            } else {
                calculatePoints();
            }
        }
    }

    private void resetAnswerData() {
        currentAnswer = null;
        adapter.resetPosition();
    }

    private void resetCounters() {
        CURRENT_QUESTION_INDEX = 0;
        currentAnswer = null;
        wrongAnswerCounter = 0;

    }

}
