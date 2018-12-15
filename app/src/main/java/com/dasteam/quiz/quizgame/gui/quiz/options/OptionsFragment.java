package com.dasteam.quiz.quizgame.gui.quiz.options;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.options_fragment, container, false);
        attachViews(view);
        initAdapter();
        setAdapterData();
        return view;
    }

    private void attachViews(View view) {
        rvOptions = view.findViewById(R.id.rv_options);
        tvQuestion = view.findViewById(R.id.tv_options_question);
    }

    private void initAdapter() {
        adapter = new OptionsAdapter();
        rvOptions.setLayoutManager(new GridLayoutManager(getContext(), 2));
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
