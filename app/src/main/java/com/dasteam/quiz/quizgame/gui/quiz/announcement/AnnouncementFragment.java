package com.dasteam.quiz.quizgame.gui.quiz.announcement;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.gui.quiz.base.BaseFragment;
import com.dasteam.quiz.quizgame.gui.quiz.closerange.CloseRangeFragment;
import com.dasteam.quiz.quizgame.gui.quiz.options.VariantsFragment;

import java.util.Objects;

import static com.dasteam.quiz.quizgame.gui.quiz.announcement.GameType.RANGE;
import static com.dasteam.quiz.quizgame.utils.QuizUtils.capitalize;

public class AnnouncementFragment extends BaseFragment {

    private TextView tvGameType;
    private TextView tvTimer;
    private AnnouncementController controller;
    private Button btnCancel;
    private ProgressBar pbContent;
    private TextView tvContent;
    private GameType type;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_announcement, container, false);
        initViews(view);
        updateData();
        setListeners();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        controller.stopCountDown();
    }

    private void setListeners() {
        btnCancel.setOnClickListener(v -> {
            controller.stopCountDown();
            Objects.requireNonNull(getActivity()).finish();
        });
    }

    private void initViews(View view) {
        tvGameType = view.findViewById(R.id.tv_game_selected_type);
        tvTimer = view.findViewById(R.id.tv_announcement_counter);
        btnCancel = view.findViewById(R.id.btn_announcement_cancel);
        pbContent = view.findViewById(R.id.pb_announcement_counter_loading);
        tvContent = view.findViewById(R.id.tv_announcement_loading_content);
        controller = new AnnouncementController();
    }

    private void updateData() {
        type = controller.generateRandomGame();
        String gameType = capitalize(type.name());
        tvGameType.setText(gameType);
        setAnnouncingTimer();
    }

    private void setAnnouncingTimer() {
        controller.setupCountDownTimer(new AnnouncementCallback() {
            @Override
            public void onTick(String second) {
                tvTimer.setText(second);
            }

            @Override
            public void onFinish() {
                switchFragment(type);
            }
        });
    }

    private void switchFragment(GameType type) {
        fragmentNavigator().replace(type == RANGE ? new CloseRangeFragment() : new VariantsFragment());
    }
}
