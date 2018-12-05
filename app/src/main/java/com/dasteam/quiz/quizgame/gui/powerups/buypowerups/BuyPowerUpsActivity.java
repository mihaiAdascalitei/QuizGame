package com.dasteam.quiz.quizgame.gui.powerups.buypowerups;

import android.annotation.SuppressLint;
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
import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.model.powerups.PowerUpsModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;
import com.dasteam.quiz.quizgame.utils.QuizUtils;

import java.util.List;

import static com.dasteam.quiz.quizgame.utils.QuizUtils.integer;
import static com.dasteam.quiz.quizgame.utils.QuizUtils.string;

public class BuyPowerUpsActivity extends BaseActivity {

    public static final String CURRENT_PLAYER = "CURRENT_PLAYER";
    private static final String MAX_POWER_COUNT = "3";

    private RecyclerView rvBuyPowerUps;
    private TextView tvTryAgain;
    private BuyPowerUpsAdapter adapter;
    private BuyPowerUpsController buyPowerUpsController;
    private PlayerModel player;

    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(RESULT_OK);
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
        getExtraData();
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
        adapter = new BuyPowerUpsAdapter(this, player.hasPremium());
    }

    private void initAdapter() {
        rvBuyPowerUps.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvBuyPowerUps.setAdapter(adapter);
        adapter.setItemBuyCallback(this::buy);
    }

    private void setAdapterData() {
        showLoading(true);
        buyPowerUpsController.getPowerUps(new DataRetriever<List<PowerUpsModel>>() {
            @Override
            public void onDataRetrieved(List<PowerUpsModel> data) {
                showLoading(false);
                setTryAgainVisibility(false);
                adapter.setData(data);
            }

            @Override
            public void onDataFailed(String message, int code) {
                showLoading(false);
                showAlert(getString(R.string.default_alert));
                setTryAgainVisibility(true);
            }
        });
    }

    private void setTryAgainVisibility(boolean visible) {
        tvTryAgain.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    private void getExtraData() {
        player = (PlayerModel) getIntent().getSerializableExtra(CURRENT_PLAYER);
    }

    private void buy(PowerUpsModel power) {
        String credit = null;

        if (!player.hasPremium()) {
            if (integer(power.getPowerPrice()) > integer(player.getCredit())) {
                showAlert(getString(R.string.not_enough_credit));
                return;
            } else {
                credit = string(integer(player.getCredit()) - integer(power.getPowerPrice()));
            }
        }

        checkPlayerPower(power, credit);
    }

    private void buyPower(String powerId, String powerCount, String powerName, String credit) {
        if (powerCount.equals(MAX_POWER_COUNT)) {
            showAlert(getString(R.string.maximum_power));
        } else {
            showLoading(true);
            buyPowerUpsController.buyPowerUps(player.getId(),
                    powerId,
                    powerCount, new DataRetriever<List<PowerUpsModel>>() {
                        @Override
                        public void onDataRetrieved(List<PowerUpsModel> data) {
                            updateCredit(player.getId(), credit);
                            String message = getString(R.string.default_buy) + " " + powerName + " power.";
                            showSnackBar(findViewById(R.id.cl_buy_power_ups_main), message);
                            showLoading(false);
                        }

                        @Override
                        public void onDataFailed(String message, int code) {
                            showLoading(false);
                            showAlert(getString(R.string.default_alert));
                        }
                    });
        }
    }

    private void updateCredit(String playerId, String credit) {
        if (credit != null) {
            buyPowerUpsController.updateCredit(playerId, credit, new DataRetriever<PlayerModel>() {
                @Override
                public void onDataRetrieved(PlayerModel data) {
                    showLoading(false);
                    player = data;
                    buyPowerUpsController.cachePlayer(data);
                }

                @Override
                public void onDataFailed(String message, int code) {
                    showLoading(false);
                    showAlert(getString(R.string.default_alert));
                }
            });
        }
    }

    private void checkPlayerPower(PowerUpsModel power, String credit) {
        buyPowerUpsController.checkPlayerPower(player.getId(), power.getPowerId(), new DataRetriever<List<PowerUpsModel>>() {
            @Override
            public void onDataRetrieved(List<PowerUpsModel> data) {
                if (data.size() == 0) {
                    buyPower(power.getPowerId(), "0", power.getPowerName(), credit);
                } else {
                    PowerUpsModel powerModel = data.get(0);
                    buyPower(powerModel.getPowerId(), powerModel.getPowerCount(), power.getPowerName(), credit);
                }

            }

            @Override
            public void onDataFailed(String message, int code) {
                showLoading(false);
                showAlert(getString(R.string.default_alert));
            }
        });
    }

}
