package com.dasteam.quiz.quizgame.gui.quiz.closerange;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.gui.quiz.base.BaseFragment;
import com.dasteam.quiz.quizgame.gui.quiz.closerange.adapter.CloseRangeAdapter;
import com.dasteam.quiz.quizgame.gui.quiz.closerange.adapter.RangeItemListener;
import com.dasteam.quiz.quizgame.utils.QuizUtils;

import java.util.ArrayList;
import java.util.List;

import static com.dasteam.quiz.quizgame.gui.quiz.closerange.adapter.CloseRangeAdapter.POSITION_REMOVE;
import static com.dasteam.quiz.quizgame.gui.quiz.closerange.adapter.CloseRangeAdapter.POSITION_ZERO;
import static com.dasteam.quiz.quizgame.utils.QuizUtils.string;

public class CloseRangeFragment extends BaseFragment {
    private RecyclerView rvItems;
    private CloseRangeAdapter adapter;
    private TextView tvInput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_close_range, container, false);
        initViews(view);
        initAdapter();
        initAdapterData();
        return view;
    }

    private void initViews(View view) {
        rvItems = view.findViewById(R.id.rv_close_range);
        tvInput = view.findViewById(R.id.tv_close_range_input);
    }

    private void initAdapter() {
        adapter = new CloseRangeAdapter(getContext());
        rvItems.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        rvItems.setNestedScrollingEnabled(false);
        rvItems.setAdapter(adapter);
    }

    private void initAdapterData() {
        List<String> data = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            data.add(string(i));
        }
        data.add(string(0)); // actual 0 value
        data.add(string(10)); // remove item value

        adapter.setData(data);
        adapter.setCallback(this::handleRangeItemListener);
    }

    private void handleRangeItemListener(int position, String value) {
        String input = tvInput.getText().toString();

        if (position == POSITION_REMOVE) {
            if (!QuizUtils.isEmpty(input)) {
                input = input.substring(0, input.length() - 1);
                tvInput.setText(input);
            }
        } else if (position == POSITION_ZERO) {
            if (input.length() > 0) {
                input += value;
                tvInput.setText(input);
            }
        } else {
            input += value;
            tvInput.setText(input);
        }
    }
}
