package com.dasteam.quiz.quizgame.gui.powerups.buypowerups;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;
import com.dasteam.quiz.quizgame.gui.powerups.buypowerups.adapter.BuyPowerUpsAdapter;
import com.dasteam.quiz.quizgame.model.powerups.PowerUpsModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;

import java.util.ArrayList;
import java.util.List;

public class BuyPowerUpsActivity extends BaseActivity {

    private RecyclerView rvBuyPowerUps;
    private TextView tvTryAgain;
    private BuyPowerUpsAdapter adapter;
    private BuyPowerUpsController buyPowerUpsController;

    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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


    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_buy_power_ups);
        configureToolbar("");
        init();
        initAdapter();
        setAdapterData();
    }

    @Override
    protected void attachViews() {
        rvBuyPowerUps = findViewById(R.id.rv_buy_power_ups);
        tvTryAgain = findViewById(R.id.tv_buy_power_ups_try_again);
    }

    @Override
    protected void attachController() {
        buyPowerUpsController = new BuyPowerUpsController();
    }

    @Override
    protected void setListeners() {
        tvTryAgain.setOnClickListener(v -> setAdapterData());
    }

    private void init() {
        adapter = new BuyPowerUpsAdapter(this);
    }

    private void initAdapter() {
        rvBuyPowerUps.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvBuyPowerUps.setAdapter(adapter);
    }

    private void setAdapterData() {
        showDialog(true);
        buyPowerUpsController.getPowerUps(new DataRetriever<List<PowerUpsModel>>() {
            @Override
            public void onDataRetrieved(List<PowerUpsModel> data) {
                showDialog(false);
                setTryAgainVisibility(false);
                adapter.setData(data);
            }

            @Override
            public void onDataFailed(String message, int code) {
                showDialog(false);
                showAlert(getString(R.string.default_alert));
                setTryAgainVisibility(true);
            }
        });
    }

    private void setTryAgainVisibility(boolean visible) {
        tvTryAgain.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}
