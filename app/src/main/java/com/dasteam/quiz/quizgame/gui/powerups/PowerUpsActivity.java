package com.dasteam.quiz.quizgame.gui.powerups;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;
import com.dasteam.quiz.quizgame.gui.powerups.adapter.PowerUpsAdapter;
import com.dasteam.quiz.quizgame.gui.powerups.buypowerups.BuyPowerUpsActivity;
import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.model.powerups.PowerUpsModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;

import java.util.List;

import static com.dasteam.quiz.quizgame.utils.QuizUtils.integer;
import static com.dasteam.quiz.quizgame.utils.QuizUtils.string;

public class PowerUpsActivity extends BaseActivity {
    public static final String POWER_UPS_PLAYER = "POWER_UPS_PLAYER";
    private static final int BUY_POWER_UPS_CODE = 10;

    private PowerUpsController powerUpsController;
    private RecyclerView rvPowerUps;
    private Button btnBuy;
    private PowerUpsAdapter adapter;
    private PlayerModel player;
    private TextView tvToolbarCredit;
    private TextView tvNoPowerUps;
    private ImageView ivNoPowerUps;
    private TextView tvTryAgain;


    @Override
    protected void onResume() {
        super.onResume();
        updateUIData();
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
        tvNoPowerUps = findViewById(R.id.tv_no_power_ups);
        ivNoPowerUps = findViewById(R.id.iv_power_ups_ninja);
        tvTryAgain = findViewById(R.id.tv_power_ups_try_again);
    }

    @Override
    protected void attachController() {
        powerUpsController = new PowerUpsController();
    }

    @Override
    protected void setListeners() {
        btnBuy.setOnClickListener(v -> buyPowerUps());
        tvTryAgain.setOnClickListener(v -> initAdapterData());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BUY_POWER_UPS_CODE) {
            if (resultCode == RESULT_OK) {
                initAdapterData();
            }
        }
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
        showLoading(true);
        powerUpsController.getPlayerPowerUps(player.getId(), new DataRetriever<List<PowerUpsModel>>() {
            @Override
            public void onDataRetrieved(List<PowerUpsModel> data) {
                showLoading(false);
                adapter.setData(data);
                setTryAgainVisibility(false);
                setNoPowerUpsVisibility(adapter.getItemCount() == 0);
            }

            @Override
            public void onDataFailed(String message, int code) {
                showLoading(false);
                setTryAgainVisibility(true);
                showAlert(getString(R.string.default_alert));
            }
        });
    }

    private void configureToolbar() {
        configureToolbar(getString(R.string.power_ups_title));
        tvToolbarCredit.setText(player.getCredit());
    }

    private void buyPowerUps() {
        startActivityForResult(new Intent(this, BuyPowerUpsActivity.class)
                .putExtra(BuyPowerUpsActivity.CURRENT_PLAYER, player), BUY_POWER_UPS_CODE);
    }

    private void sell(PowerUpsModel power, int position) {
        String credit = null;
        if (!player.hasPremium()) {
            credit = string(integer(power.getPowerPrice()) + integer(player.getCredit()));
            player.setCredit(credit);
            tvToolbarCredit.setText(credit);
        }
        sellPower(power, credit);
    }

    private void sellPower(PowerUpsModel power, String credit) {
        showLoading(true);
        powerUpsController.sellPowerUps(power.getPlayerId(), power.getPowerId(), power.getPowerCount(), new DataRetriever<List<PowerUpsModel>>() {
            @Override
            public void onDataRetrieved(List<PowerUpsModel> data) {
                adapter.setData(data);
                setNoPowerUpsVisibility(adapter.getItemCount() == 0);
                updateCredit(power.getPlayerId(), credit);

            }

            @Override
            public void onDataFailed(String message, int code) {
                showLoading(false);
                showAlert(getString(R.string.default_alert));

            }
        });
    }


    private void setNoPowerUpsVisibility(boolean visible) {
        tvNoPowerUps.setVisibility(visible ? View.VISIBLE : View.GONE);
        ivNoPowerUps.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    private void setTryAgainVisibility(boolean visibile) {
        tvTryAgain.setVisibility(visibile ? View.VISIBLE : View.GONE);
    }

    private void updateCredit(String playerId, String credit) {
        if (credit != null) {
            powerUpsController.updateCredit(playerId, credit, new DataRetriever<PlayerModel>() {
                @Override
                public void onDataRetrieved(PlayerModel data) {
                    showLoading(false);
                    player = data;
                    powerUpsController.cachePlayer(data);
                }

                @Override
                public void onDataFailed(String message, int code) {
                    showLoading(false);
                    showAlert(getString(R.string.default_alert));
                }
            });
        } else {
            showLoading(false);
        }
    }


    private void updateUIData() {
        player = powerUpsController.getPlayer();
        tvToolbarCredit.setText(player.getCredit());

    }
}
