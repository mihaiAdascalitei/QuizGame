package com.dasteam.quiz.quizgame.gui.quiz.options;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;
import com.dasteam.quiz.quizgame.gui.quiz.options.adpater.VariantsAdapter;
import com.dasteam.quiz.quizgame.model.question.AnswerModel;
import com.dasteam.quiz.quizgame.model.question.QuestionModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;

import java.util.List;
import java.util.Objects;

public class VariantsFragment extends Fragment {

    private RecyclerView rvOptions;
    private TextView tvQuestion;
    private VariantsAdapter adapter;
    private VariantsController controller;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_options, container, false);
        attachViews(view);
        initAdapter();
        getQuestions();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.main_screen_menu, menu);
    }

    private void attachViews(View view) {
        rvOptions = view.findViewById(R.id.rv_options);
        tvQuestion = view.findViewById(R.id.tv_options_question);
        controller = new VariantsController();
    }

    private void initAdapter() {
        adapter = new VariantsAdapter();
        rvOptions.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rvOptions.setAdapter(adapter);
    }

    private void initQuestionData(List<QuestionModel> data) {
        tvQuestion.setText(data.get(0).getQuestion());
        adapter.setData(data.get(0).getAnswers());
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
                initQuestionData(data);
            }

            @Override
            public void onDataFailed(String message, int code) {
                showLoading(false);
            }
        });
    }

}
