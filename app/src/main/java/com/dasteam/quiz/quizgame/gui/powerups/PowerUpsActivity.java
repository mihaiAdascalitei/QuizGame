package com.dasteam.quiz.quizgame.gui.powerups;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;
import com.dasteam.quiz.quizgame.gui.powerups.adapter.PowerUpsAdapter;
import com.dasteam.quiz.quizgame.gui.powerups.buypowerups.BuyPowerUpsActivity;
import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.model.powerups.PowerUpsModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;
import com.dasteam.quiz.quizgame.utils.QuizUtils;

import java.util.List;

import static com.dasteam.quiz.quizgame.utils.QuizUtils.integer;
import static com.dasteam.quiz.quizgame.utils.QuizUtils.string;

public class PowerUpsActivity extends BaseActivity {
    public static final String POWER_UPS_PLAYER = "POWER_UPS_PLAYER";

    private PowerUpsController powerUpsController;
    private RecyclerView rvPowerUps;
    private Button btnBuy;
    private PowerUpsAdapter adapter;
    private PlayerModel player;
    private TextView tvToolbarCredit;

    @Override
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
        init();
        configureToolbar();
        initAdapter();
        initAdapterData();
    }

    @Override
    protected void attachViews() {
        btnBuy = findViewById(R.id.btn_buy_power_ups);
        rvPowerUps = findViewById(R.id.rv_power_ups);
        tvToolbarCredit = findViewById(R.id.tv_power_ups_toolbar_credit);
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
        adapter = new PowerUpsAdapter(this);
        player = (PlayerModel) getIntent().getSerializableExtra(POWER_UPS_PLAYER);
    }

    private void initAdapter() {
        rvPowerUps.setLayoutManager(new LinearLayoutManager(this));
        rvPowerUps.setAdapter(adapter);
        adapter.setCallback(this::sell);

    }

    private void initAdapterData() {
        showDialog(true);
        powerUpsController.getPlayerPowerUps(player.getId(), new DataRetriever<List<PowerUpsModel>>() {
            @Override
            public void onDataRetrieved(List<PowerUpsModel> data) {
                showDialog(false);
                adapter.setData(data);
            }

            @Override
            public void onDataFailed(String message, int code) {
                showDialog(false);
                showAlert(getString(R.string.default_alert));
            }
        });
    }

    private void configureToolbar() {
        configureToolbar(getString(R.string.power_ups_title));
        tvToolbarCredit.setText(player.getCredit());
    }

    private void buyPowerUps() {
        startActivity(new Intent(this, BuyPowerUpsActivity.class).putExtra(BuyPowerUpsActivity.CURRENT_PLAYER, player));
    }

    private void sell(PowerUpsModel power, int position) {
        String credit = string(integer(power.getPowerPrice()) + integer(player.getCredit()));
        tvToolbarCredit.setText(credit);
        adapter.removeItem(power, position);
    }
}
