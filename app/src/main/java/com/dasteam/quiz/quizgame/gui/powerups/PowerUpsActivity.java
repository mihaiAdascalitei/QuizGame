package com.dasteam.quiz.quizgame.gui.powerups;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;
import com.dasteam.quiz.quizgame.gui.powerups.adapter.PowerUpsAdapter;
import com.dasteam.quiz.quizgame.gui.powerups.buypowerups.BuyPowerUpsActivity;
import com.dasteam.quiz.quizgame.model.powerups.PowerUpsModel;

import java.util.ArrayList;
import java.util.List;

public class PowerUpsActivity extends BaseActivity {


    private PowerUpsController powerUpsController;
    private RecyclerView rvPowerUps;
    private Button btnBuy;
    private PowerUpsAdapter adapter;

    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_keyboard_backspace);
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
        super.onCreate(savedInstanceState, R.layout.activity_power_ups);
        configureToolbar(getString(R.string.power_ups_title));
        init();
        initAdapter();
        initAdapterData();
    }

    @Override
    protected void attachViews() {
        btnBuy = findViewById(R.id.btn_buy_power_ups);
        rvPowerUps = findViewById(R.id.rv_power_ups);
    }

    @Override
    protected void attachController() {
        powerUpsController = new PowerUpsController();
    }

    @Override
    protected void setListeners() {
        btnBuy.setOnClickListener(v -> buyPowerUps());
    }

    private void init() {
        adapter = new PowerUpsAdapter();
    }

    private void initAdapter() {
        rvPowerUps.setLayoutManager(new LinearLayoutManager(this));
        rvPowerUps.setAdapter(adapter);
    }

    private void initAdapterData() {
        List<PowerUpsModel> dummyData = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            PowerUpsModel powerUpsModel = new PowerUpsModel();
            powerUpsModel.setPowerName("Power " + String.valueOf(i));
            dummyData.add(powerUpsModel);
        }
        adapter.setData(dummyData);
    }

    private void buyPowerUps() {
        startActivity(new Intent(this, BuyPowerUpsActivity.class));
    }
}
