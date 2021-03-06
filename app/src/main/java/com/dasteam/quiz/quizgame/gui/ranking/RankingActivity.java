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

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_ranking);
        init();
        initAdapter();
        configureToolbar(getString(R.string.ranking));
        setAdapterData();
        setConnectionErrorIfAvailable();
    }

    @Override
    protected void attachViews() {
        rvRanking = findViewById(R.id.rv_ranking);

    }

    @Override
    protected void attachController() {
        rankingController = new RankingController();
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void connectionErrorListener() {
        setAdapterData();
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
                showConnectionError(false);
                rankingAdapter.setData(data);
            }

            @Override
            public void onDataFailed(String message, int code) {
                showLoading(false);
                showConnectionError(true);
                showAlert(getString(R.string.default_alert));
            }
        });
    }


}

