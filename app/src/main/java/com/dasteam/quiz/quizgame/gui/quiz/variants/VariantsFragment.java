package com.dasteam.quiz.quizgame.gui.quiz.variants;

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
import com.dasteam.quiz.quizgame.gui.quiz.QuizActivity;
import com.dasteam.quiz.quizgame.gui.quiz.base.BaseFragment;
import com.dasteam.quiz.quizgame.gui.quiz.endgame.EndGameFragment;
import com.dasteam.quiz.quizgame.gui.quiz.nolives.NoLivesFragment;
import com.dasteam.quiz.quizgame.gui.quiz.powerups.PowerUpsQuizActivity;
import com.dasteam.quiz.quizgame.gui.quiz.variants.adpater.VariantsAdapter;
import com.dasteam.quiz.quizgame.model.question.AnswerModel;
import com.dasteam.quiz.quizgame.model.question.QuestionModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;

import java.util.List;
import java.util.Objects;

import static com.dasteam.quiz.quizgame.utils.QuizUtils.integer;
import static com.dasteam.quiz.quizgame.utils.QuizUtils.saveArrayAsStringJson;
import static com.dasteam.quiz.quizgame.utils.QuizUtils.string;

public class VariantsFragment extends BaseFragment {

    private static int CURRENT_QUESTION_INDEX = 0;
    private static final int MAX_INITIAL_LIVES = 2;
    private static final int POWER_UPS_REQUEST_CODEW = 11;

    private RecyclerView rvOptions;
    private TextView tvQuestion;
    private TextView tvSubmit;
    private TextView tvPoints;
    private TextView tvLives;
    private TextView tvError;
    private TextView tvSurrender;
    private TextView tvPowerUps;

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
        tvError = view.findViewById(R.id.tv_options_error);
        tvSurrender = view.findViewById(R.id.tv_option_quit);
        tvPowerUps = view.findViewById(R.id.tv_option_power_up);
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
        tvError.setOnClickListener(v -> getQuestions());
        tvSurrender.setOnClickListener(v -> ((QuizActivity) Objects.requireNonNull(getContext())).showSurrenderAlert());
        tvPowerUps.setOnClickListener(v -> openPowerUps());
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
                showError(false);
                questions = data;
                updateQuestions();
            }

            @Override
            public void onDataFailed(String message, int code) {
                showError(true);
                showLoading(false);
            }
        });
    }

    private void submit() {
        if (questions != null && currentAnswer != null) {
            markSelectedAnswer();
            checkCountersAndPoints();

            if (wrongAnswerCounter == MAX_INITIAL_LIVES) {
                fragmentNavigator().replace(new NoLivesFragment());
            } else {
                CURRENT_QUESTION_INDEX++;
                if (CURRENT_QUESTION_INDEX == questions.size()) {
                    showEndGame();
                } else {
                    updateQuestions();
                    resetAnswerData();
                }
            }
        }
    }

    private void markSelectedAnswer() {
        int p = adapter.getSelectedPosition();
        if (p != -1) {
            questions.get(CURRENT_QUESTION_INDEX).getAnswers().get(p).setSelected(true);
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
        if (!currentAnswer.isCorrect()) {
            wrongAnswerCounter++;
            decrementLives();
        } else {
            calculatePoints();
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

    private void showError(boolean show) {
        tvError.setVisibility(show ? View.VISIBLE : View.GONE);
        rvOptions.setVisibility(show ? View.GONE : View.VISIBLE);
        tvQuestion.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    private void showEndGame() {
        Bundle args = new Bundle();
        args.putString(EndGameFragment.END_GAME_POINTS, tvPoints.getText().toString());
        args.putString(EndGameFragment.END_GAME_LIST, saveArrayAsStringJson(questions));
        fragmentNavigator().replace(new EndGameFragment(), args);
    }

    private void openPowerUps() {
        startActivityForResult(new Intent(getContext(), PowerUpsQuizActivity.class), POWER_UPS_REQUEST_CODEW);
    }
}
