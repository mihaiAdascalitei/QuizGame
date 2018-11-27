package com.dasteam.quiz.quizgame.gui.lobby;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;
import com.dasteam.quiz.quizgame.gui.lobby.adapter.LobbyAdapter;
import com.dasteam.quiz.quizgame.model.player.LobbyPlayerModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LobbyActivity extends BaseActivity {

    private LobbyController lobbyController;
    private LobbyAdapter lobbyAdapter;
    private RecyclerView rvLobby;
    private TextView tvPlayerCount;

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_lobby);
        init();
        initAdapter();
        configureToolbar(getString(R.string.lobby));
    }

    @Override
    protected void attachViews() {
        rvLobby = findViewById(R.id.rv_lobby);
        tvPlayerCount = findViewById(R.id.tv_lobby_count_players);
    }

    @Override
    protected void attachController() {
        lobbyController = new LobbyController();
    }

    @Override
    protected void setListeners() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_keyboard_backspace);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void init() {
        lobbyAdapter = new LobbyAdapter(this);
    }

    private void initAdapter() {
        rvLobby.setLayoutManager(new LinearLayoutManager(this));
        rvLobby.setAdapter(lobbyAdapter);
        Random testRandom = new Random();
        List<LobbyPlayerModel> dummyData = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            LobbyPlayerModel player = new LobbyPlayerModel();
            player.setUsername("Player " + String.valueOf(i));
            player.setRank(testRandom.nextInt(4));
            dummyData.add(player);
        }

        lobbyAdapter.setData(dummyData);

        tvPlayerCount.setText(String.valueOf(lobbyAdapter.getItemCount()));
    }
}
