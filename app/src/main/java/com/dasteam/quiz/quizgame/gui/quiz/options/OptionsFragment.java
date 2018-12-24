package com.dasteam.quiz.quizgame.gui.quiz.options;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.gui.quiz.options.adpater.OptionsAdapter;

import java.util.ArrayList;
import java.util.List;

public class OptionsFragment extends Fragment {

    private RecyclerView rvOptions;
    private TextView tvQuestion;
    private OptionsAdapter adapter;
    private Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_options, container, false);
        attachViews(view);
        initAdapter();
        setAdapterData();
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
        toolbar = view.findViewById(R.id.toolbar);
    }

    private void initAdapter() {
        adapter = new OptionsAdapter();
        rvOptions.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rvOptions.setAdapter(adapter);
    }

    private void setAdapterData() {
        List<String> dummyData = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            dummyData.add("");
        }

        adapter.setData(dummyData);
    }
}
