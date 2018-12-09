package com.dasteam.quiz.quizgame.gui.ranking;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;
import com.dasteam.quiz.quizgame.gui.ranking.adapter.RankingAdapter;
import com.dasteam.quiz.quizgame.model.player.LobbyPlayerModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    private void init() {
        rankingAdapter = new RankingAdapter(this);
    }

    private void initAdapter() {
        rvRanking.setLayoutManager(new LinearLayoutManager(this));
        rvRanking.setAdapter(rankingAdapter);
        Random testRandom = new Random();
        List<LobbyPlayerModel> dummyData = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            LobbyPlayerModel player = new LobbyPlayerModel();
            player.setUsername("Player " + String.valueOf(i));
            player.setRank(testRandom.nextInt(4));
            dummyData.add(player);
        }

        rankingAdapter.setData(dummyData);
    }
}

