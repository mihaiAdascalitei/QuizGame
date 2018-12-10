package com.dasteam.quiz.quizgame.gui.ranking;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;
import com.dasteam.quiz.quizgame.gui.ranking.adapter.RankingAdapter;
import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;

import java.util.List;

public class RankingActivity extends BaseActivity {

    private RecyclerView rvRanking;
    private RankingAdapter rankingAdapter;
    private RankingController rankingController;
    private LinearLayout llError;

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_ranking);
        init();
        initAdapter();
        configureToolbar(getString(R.string.ranking));
        setAdapterData();
    }

    @Override
    protected void attachViews() {
        rvRanking = findViewById(R.id.rv_ranking);
        llError = findViewById(R.id.ll_ranking_error);

    }

    @Override
    protected void attachController() {
        rankingController = new RankingController();
    }

    @Override
    protected void setListeners() {
        llError.setOnClickListener(v -> setAdapterData());
    }

    private void init() {
        rankingAdapter = new RankingAdapter(this);
    }

    private void initAdapter() {
        rvRanking.setLayoutManager(new LinearLayoutManager(this));
        rvRanking.setAdapter(rankingAdapter);
    }


    private void setAdapterData() {
        showLoading(true);
        rankingController.getRankingPlayers(new DataRetriever<List<PlayerModel>>() {
            @Override
            public void onDataRetrieved(List<PlayerModel> data) {
                showLoading(false);
                setConnectionError(false);
                rankingAdapter.setData(data);
            }

            @Override
            public void onDataFailed(String message, int code) {
                showLoading(false);
                setConnectionError(true);
                showAlert(getString(R.string.default_alert));
            }
        });
    }

    private void setConnectionError(boolean visible) {
        llError.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}

