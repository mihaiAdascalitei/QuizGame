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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;
import static com.dasteam.quiz.quizgame.gui.quiz.powerups.PowerUpsQuizActivity.HALF_RESULT;
import static com.dasteam.quiz.quizgame.gui.quiz.powerups.PowerUpsQuizActivity.HEART_RESULT;
import static com.dasteam.quiz.quizgame.gui.quiz.powerups.PowerUpsQuizActivity.RANDOM_RESULT;
import static com.dasteam.quiz.quizgame.gui.quiz.powerups.PowerUpsQuizActivity.SUGGESTION_RESULT;
import static com.dasteam.quiz.quizgame.gui.quiz.powerups.PowerUpsQuizActivity.SWITCH_RESULT;
import static com.dasteam.quiz.quizgame.gui.quiz.powerups.PowerUpsQuizActivity.TIMER_RESULT;
import static com.dasteam.quiz.quizgame.utils.QuizUtils.integer;
import static com.dasteam.quiz.quizgame.utils.QuizUtils.saveArrayAsStringJson;
import static com.dasteam.quiz.quizgame.utils.QuizUtils.string;

public class VariantsFragment extends BaseFragment {

    private static int CURRENT_QUESTION_INDEX = 0;
    private static int MAX_INITIAL_LIVES = 2;
    private static final int POWER_UPS_REQUEST_CODE = 11;

    private RecyclerView rvOptions;
    private TextView tvQuestion;
    private TextView tvSubmit;
    private TextView tvPoints;
    private TextView tvLives;
    private TextView tvError;
    private TextView tvSurrender;
    private TextView tvPowerUps;
    private TextView tvTimer;

    private VariantsAdapter adapter;
    private VariantsController controller;
    private List<QuestionModel> questions;
    private AnswerModel currentAnswer = null;
    private boolean powerHasBeenUsed = false;
    private List<Integer> questionPosition;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_options, container, false);
        resetCounters();
        attachViews(view);
        initAdapter();
        getQuestions();
        setListeners();
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
        tvTimer = view.findViewById(R.id.tv_options_timer);
        controller = new VariantsController();
        tvLives.setText(string(MAX_INITIAL_LIVES));
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
        if (CURRENT_QUESTION_INDEX >= questions.size()) {
            for (Integer p : questionPosition) {
                CURRENT_QUESTION_INDEX = p;
            }
        }
        tvQuestion.setText(questions.get(CURRENT_QUESTION_INDEX).getQuestion());
        adapter.setData(questions.get(CURRENT_QUESTION_INDEX).getAnswers());
        powerHasBeenUsed = false;

    }

    private void updateQuestionsPosition() {
        questionPosition = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
            questionPosition.add(i);
        }
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
                updateQuestionsPosition();
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
            removeQuestionPosition();
            if (MAX_INITIAL_LIVES == 0) {
                fragmentNavigator().replace(new NoLivesFragment());
            } else {
                CURRENT_QUESTION_INDEX = getNextAvailablePosition();
                if (questionPosition.size() == 0) {
                    showEndGame();
                } else {
                    updateQuestions();
                    resetAnswerData();
                }
            }
        }
    }

    private int getNextAvailablePosition() {
        if (questionPosition.size() != 0) {
            Integer pos = questionPosition.get(0);
            return pos == null ? -1 : pos;
        }
        return -1;
    }

    private int getNextPositionFrom(int current) {
        Integer pos = questionPosition.get(current + 1);
        return pos == null ? -1 : pos;
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

    private void removeQuestionPosition() {
        Integer pos = null;
        for (Integer p : questionPosition) {
            if (p == CURRENT_QUESTION_INDEX) {
                pos = p;
                break;
            }
        }
        if (pos != null) {
            questionPosition.remove(pos);
        }
    }

    private void decrementLives() {
        tvLives.setText(string(--MAX_INITIAL_LIVES));
    }

    private void checkCountersAndPoints() {
        if (!currentAnswer.isCorrect()) {
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
        MAX_INITIAL_LIVES = 2;
        currentAnswer = null;
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
        if (!powerHasBeenUsed) {
            startActivityForResult(new Intent(getContext(), PowerUpsQuizActivity.class), POWER_UPS_REQUEST_CODE);
        } else {
            showDelayedAlert(getString(R.string.only_one_power));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == POWER_UPS_REQUEST_CODE) {
            if (resultCode != RESULT_OK) {
                powerHasBeenUsed = true;
            }
            handleResult(resultCode);
        }
    }

    private void handleResult(int resultCode) {
        switch (resultCode) {
            case TIMER_RESULT:
                tvTimer.setText(controller.getRandomTimerBonus(tvTimer.getText().toString()));
                break;
            case HALF_RESULT:
                adapter.setData(controller.halfData(questions.get(CURRENT_QUESTION_INDEX).getAnswers()));
                break;
            case HEART_RESULT:
                tvLives.setText(string(++MAX_INITIAL_LIVES));
                break;
            case SUGGESTION_RESULT:
                setSuggestionResult();
                break;
            case RANDOM_RESULT:
                setRandomResult();
                break;
            case SWITCH_RESULT:
                switchQuestion();
                break;
            default:
                break;
        }
    }

    private void setRandomResult() {
        int randomPos = controller.getRandomAnswer(questions.get(CURRENT_QUESTION_INDEX).getAnswers());
        adapter.setPosition(randomPos);
        currentAnswer = questions.get(CURRENT_QUESTION_INDEX).getAnswers().get(randomPos);
    }

    private void setSuggestionResult() {
        List<AnswerModel> answers = questions.get(CURRENT_QUESTION_INDEX).getAnswers();
        int randomPos = controller.getRandomWrongAnswer(answers);
        answers.get(randomPos).setStrikeThru(true);
        adapter.setData(answers);
    }

    private void switchQuestion() {
        if (CURRENT_QUESTION_INDEX < questions.size()) {
            CURRENT_QUESTION_INDEX = getNextPositionFrom(CURRENT_QUESTION_INDEX);
        }
        updateQuestions();
        resetAnswerData();
    }
}
