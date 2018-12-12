package com.dasteam.quiz.quizgame.gui.feedback.history;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;
import com.dasteam.quiz.quizgame.gui.feedback.history.adapter.FeedbackHistoryAdapter;
import com.dasteam.quiz.quizgame.gui.feedback.history.adapter.FeedbackHistoryItemClick;
import com.dasteam.quiz.quizgame.model.feedback.FeedbackModel;
import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;

import java.util.List;

public class FeedbackHistoryActivity extends BaseActivity {

    public static final String HISTORY_PLAYER = "HISTORY_PLAYER";
    private PlayerModel player;
    private RecyclerView rvHistory;
    private FeedbackHistoryController historyController;
    private FeedbackHistoryAdapter adapter;
    private TextView tvNoFeedback;
    private ImageView ivNoFeedback;


    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_feedback_history);
        configureToolbar(getString(R.string.feedback_history));
        getExtraData();
        initAdapter();
        setAdapterData();
        setConnectionErrorIfAvailable();
    }

    @Override
    protected void attachViews() {
        rvHistory = findViewById(R.id.rv_feedback_history);
        tvNoFeedback = findViewById(R.id.tv_feedback_history_no_feedback);
        ivNoFeedback = findViewById(R.id.iv_feedback_history_no_feedback);
    }

    @Override
    protected void attachController() {
        historyController = new FeedbackHistoryController();
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void connectionErrorListener() {
        setAdapterData();
    }

    private void getExtraData() {
        player = (PlayerModel) getIntent().getSerializableExtra(HISTORY_PLAYER);
    }

    private void initAdapter() {
        adapter = new FeedbackHistoryAdapter();
        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        rvHistory.setAdapter(adapter);
        adapter.setCallback(this::removeFeedbackItem);
    }

    private void setAdapterData() {
        showLoading(true);
        historyController.getPlayerFeedback(player.getId(), new DataRetriever<List<FeedbackModel>>() {
            @Override
            public void onDataRetrieved(List<FeedbackModel> data) {
                showLoading(false);
                showConnectionError(false);
                adapter.setData(data);
                setNoFeedbackVisibility(data.size() == 0);

            }

            @Override
            public void onDataFailed(String message, int code) {
                showLoading(false);
                showConnectionError(true);
                setNoFeedbackVisibility(false);
                showAlert(getString(R.string.default_alert));
            }
        });
    }

    private void removeFeedbackItem(FeedbackModel feedback) {
        showLoading(true);
        historyController.removeFeedback(feedback.getFeedbackId(), player.getId(), new DataRetriever<List<FeedbackModel>>() {
            @Override
            public void onDataRetrieved(List<FeedbackModel> data) {
                showLoading(false);
                adapter.setData(data);
                setNoFeedbackVisibility(data.size() == 0);
            }

            @Override
            public void onDataFailed(String message, int code) {
                showLoading(false);
                showAlert(getString(R.string.default_alert));
            }
        });
    }

    private void setNoFeedbackVisibility(boolean visible) {
        tvNoFeedback.setVisibility(visible ? View.VISIBLE : View.GONE);
        ivNoFeedback.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

}
