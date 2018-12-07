package com.dasteam.quiz.quizgame.gui.premium;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;
import com.dasteam.quiz.quizgame.gui.premium.status.PremiumValidateStatus;
import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.gui.premium.picker.DatePickerDialog;
import com.dasteam.quiz.quizgame.network.DataRetriever;

import static com.dasteam.quiz.quizgame.utils.QuizUtils.getDaysDifference;

public class PremiumAccountActivity extends BaseActivity {
    public static final String PREMIUM_PLAYER = "PREMIUM_PLAYER";
    public static final int MAX_PREMIUM_DAY_LIMIT = 10;
    private PremiumAccountController accountController;
    private PlayerModel player;

    private ConstraintLayout clCardForm;
    private EditText etCardNumber;
    private TextView tvCardExpDate;
    private EditText etCardCcv;
    private Button btnBuyPremium;
    private TextView tvCardNumberAlert;
    private TextView tvBottomAlert;
    private TextView tvAlreadyPremium;
    private ImageView ivAlreadyPremium;
    private TextView tvCheckDetails;
    private ConstraintLayout clDetails;
    private ImageView ivDetailsClose;
    private TextView tvExpand;
    private TextView tvDaysLeft;

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
        super.onCreate(savedInstanceState, R.layout.activity_premium_account);
        configureToolbar(getString(R.string.activate_premium));
        getExtraData();
        setPremiumMode();
    }

    @Override
    protected void attachViews() {
        clCardForm = findViewById(R.id.cv_card_form);
        etCardCcv = findViewById(R.id.et_ccv);
        tvCardExpDate = findViewById(R.id.tv_exp_date);
        etCardNumber = findViewById(R.id.et_card_number);
        btnBuyPremium = findViewById(R.id.btn_premium_account);
        tvCardNumberAlert = findViewById(R.id.tv_premium_card_number_alert);
        tvBottomAlert = findViewById(R.id.tv_premium_bottom_alert);
        tvAlreadyPremium = findViewById(R.id.tv_premium_activated);
        ivAlreadyPremium = findViewById(R.id.iv_premium_activated);
        tvCheckDetails = findViewById(R.id.tv_premium_check_details);
        clDetails = findViewById(R.id.cl_premium_details);
        ivDetailsClose = findViewById(R.id.iv_details_close);
        tvExpand = findViewById(R.id.tv_premium_expand);
        tvDaysLeft = findViewById(R.id.tv_premium_days_left);
    }

    @Override
    protected void attachController() {
        accountController = new PremiumAccountController();
    }

    @Override
    protected void setListeners() {
        btnBuyPremium.setOnClickListener(v -> buyPremium());
        tvCardExpDate.setOnClickListener(v -> popupDate());
        tvCheckDetails.setOnClickListener(v -> checkDetails());
        ivDetailsClose.setOnClickListener(v -> closeDetails());
    }

    private void getExtraData() {
        player = (PlayerModel) getIntent().getSerializableExtra(PREMIUM_PLAYER);
    }

    private void setPremiumMode() {

        boolean hasPremium = player.hasPremium();
        clCardForm.setVisibility(hasPremium ? View.GONE : View.VISIBLE);
        tvAlreadyPremium.setVisibility(hasPremium ? View.VISIBLE : View.GONE);
        ivAlreadyPremium.setVisibility(hasPremium ? View.VISIBLE : View.GONE);
        tvCheckDetails.setVisibility(hasPremium ? View.VISIBLE : View.GONE);

        handleTimeleft();
    }

    private void buyPremium() {
        String cardNumber = etCardNumber.getText().toString();
        String ccv = etCardCcv.getText().toString();
        String expDate = tvCardExpDate.getText().toString();

        accountController.validateData(cardNumber, ccv, expDate, this::handlePremiumResponse);
    }

    private void handlePremiumResponse(PremiumValidateStatus status) {
        switch (status) {
            case SUCCESS:
                makeAccountAsPremium();
                break;
            case CCV_LENGTH:
                showCcvAlert();
                break;
            case CREDIT_CARD_LENGTH:
                showCardNumberAlert();
                break;
            case EMPTY_DATE:
                showDateAlert();
                break;
            default:
                break;

        }
    }

    private void showCardNumberAlert() {
        hideAlerts();
        tvCardNumberAlert.setVisibility(View.VISIBLE);
    }

    private void showCcvAlert() {
        hideAlerts();
        tvBottomAlert.setText(getString(R.string.ccv_alert));
        tvBottomAlert.setVisibility(View.VISIBLE);
    }

    private void hideAlerts() {
        tvBottomAlert.setVisibility(View.GONE);
        tvCardNumberAlert.setVisibility(View.GONE);
    }

    private void showDateAlert() {
        hideAlerts();
        tvBottomAlert.setText(getString(R.string.exp_date_alert));
        tvBottomAlert.setVisibility(View.VISIBLE);
    }

    private void popupDate() {
        new DatePickerDialog(this, date -> tvCardExpDate.setText(date)).show();
    }

    private void makeAccountAsPremium() {
        hideAlerts();
        showLoading(true);
        accountController.makePremium(player.getId(), new DataRetriever<PlayerModel>() {
            @Override
            public void onDataRetrieved(PlayerModel data) {
                showLoading(false);
                accountController.cachePlayer(data);
                player = data;
                setPremiumMode();
            }

            @Override
            public void onDataFailed(String message, int code) {
                showLoading(false);
                showAlert(getString(R.string.default_alert));
            }
        });
    }

    private void checkDetails() {
        tvCheckDetails.setVisibility(View.GONE);
        clDetails.setVisibility(View.VISIBLE);
    }

    private void closeDetails() {
        clDetails.setVisibility(View.GONE);
        tvCheckDetails.setVisibility(View.VISIBLE);
    }

    private void handleTimeleft() {
        if (player.hasPremium()) {
            String premiumDate = player.getPremiumDateActivated();
            if (premiumDate != null) {
                int differenceDay = getDaysDifference(premiumDate);
                String daysLeft = String.valueOf(MAX_PREMIUM_DAY_LIMIT - differenceDay) + " " + getString(R.string.days);
                tvDaysLeft.setText(daysLeft);
            }
        }
    }
}
