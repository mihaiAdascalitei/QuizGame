package com.dasteam.quiz.quizgame.gui.quiz.powerups;

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
import com.dasteam.quiz.quizgame.gui.quiz.powerups.adapter.PowerUpsItemListener;
import com.dasteam.quiz.quizgame.gui.quiz.powerups.adapter.PowerUpsQuizAdapter;
import com.dasteam.quiz.quizgame.model.powerups.PowerUpsModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;

import java.util.List;

public class PowerUpsQuizActivity extends BaseActivity {
    private static final String TIMER_BONUS = "timer_bonus";
    private static final String SWITCH_BONUS = "switch_bonus";
    private static final String RANDOM_BONUS = "random_bonus";
    private static final String SUGGESTION_BONUS = "suggestion_bonus";
    private static final String HALF_BONUS = "half_bonus";
    private static final String BUY_BONUS = "buy_bonus";
    private static final String HEART_BONUS = "heart_bonus";

    public static final int TIMER_RESULT = 4;
    public static final int SWITCH_RESULT = 5;
    public static final int RANDOM_RESULT = 6;
    public static final int SUGGESTION_RESULT = 7;
    public static final int HALF_RESULT = 8;
    public static final int HEART_RESULT = 9;


    private RecyclerView rvPowerUps;
    private PowerUpsQuizController controller;
    private PowerUpsQuizAdapter adapter;
    private TextView tvNoPowerUps;

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_power_ups_quiz);
        configureToolbar("");
        initAdapter();
        setAdapterData();
        setConnectionErrorIfAvailable();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
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

    @Override
    protected void attachViews() {
        rvPowerUps = findViewById(R.id.rv_quiz_power_ups);
        tvNoPowerUps = findViewById(R.id.tv_no_power_ups);
    }

    @Override
    protected void attachController() {
        controller = new PowerUpsQuizController();
    }

    @Override
    protected void setListeners() {

    }

    private void initAdapter() {
        adapter = new PowerUpsQuizAdapter(this);
        rvPowerUps.setLayoutManager(new LinearLayoutManager(this));
        rvPowerUps.setAdapter(adapter);
        adapter.setCallback(this::usePowerUp);

    }

    private void setAdapterData() {
        showLoading(true);
        controller.getPlayerPowerUps(controller.getPlayer().getId(), new DataRetriever<List<PowerUpsModel>>() {
            @Override
            public void onDataRetrieved(List<PowerUpsModel> data) {
                showLoading(false);
                adapter.setData(data);
                showConnectionError(false);
                setNoPowerUpsVisibility(adapter.getItemCount() == 0);
            }

            @Override
            public void onDataFailed(String message, int code) {
                showLoading(false);
                showConnectionError(true);
                showAlert(getString(R.string.default_alert));
            }
        });
    }

    private void setNoPowerUpsVisibility(boolean visibility) {
        tvNoPowerUps.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    private void usePowerUp(PowerUpsModel power) {
        showLoading(true);
        controller.usePowerUp(power.getPlayerId(), power.getPowerId(), power.getPowerCount(), new DataRetriever<List<PowerUpsModel>>() {
            @Override
            public void onDataRetrieved(List<PowerUpsModel> data) {
                adapter.setData(data);
                setNoPowerUpsVisibility(adapter.getItemCount() == 0);
                handlePowerUp(power.getPowerIconKey());

            }

            @Override
            public void onDataFailed(String message, int code) {
                showLoading(false);
                showAlert(getString(R.string.default_alert));

            }
        });
    }

    private void handlePowerUp(String power) {
        switch (power) {
            case TIMER_BONUS:
                exitWithResult(TIMER_RESULT);
                break;
            case SWITCH_BONUS:
                exitWithResult(SWITCH_RESULT);
                break;
            case SUGGESTION_BONUS:
                exitWithResult(SUGGESTION_RESULT);
                break;
            case RANDOM_BONUS:
                exitWithResult(RANDOM_RESULT);
                break;
            case HALF_BONUS:
                exitWithResult(HALF_RESULT);
                break;
            case BUY_BONUS:
                break;
            case HEART_BONUS:
                exitWithResult(HEART_RESULT);
                break;
            default:
                break;
        }
    }

    private void exitWithResult(int result) {
        setResult(result);
        finish();
    }
}
