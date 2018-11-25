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
import com.dasteam.quiz.quizgame.gui.premium.picker.DatePickerCallback;
import com.dasteam.quiz.quizgame.gui.premium.status.PremiumValidateStatus;
import com.dasteam.quiz.quizgame.model.PlayerModel;
import com.dasteam.quiz.quizgame.gui.premium.picker.DatePickerDialog;

public class PremiumAccountActivity extends BaseActivity {
    public static final String PREMIUM_PLAYER = "PREMIUM_PLAYER";

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
    }

    @Override
    protected void attachController() {
        accountController = new PremiumAccountController();
    }

    @Override
    protected void setListeners() {
        btnBuyPremium.setOnClickListener(v -> buyPremium());
        tvCardExpDate.setOnClickListener(v -> popupDate());
    }

    private void getExtraData() {
        player = (PlayerModel) getIntent().getSerializableExtra(PREMIUM_PLAYER);
    }

    private void setPremiumMode() {

        boolean hasPremium = player.hasPremium() == 1;
        clCardForm.setVisibility(hasPremium ? View.GONE : View.VISIBLE);
        tvAlreadyPremium.setVisibility(hasPremium ? View.VISIBLE : View.GONE);
        ivAlreadyPremium.setVisibility(hasPremium ? View.VISIBLE : View.GONE);
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
                hideAlerts();
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
}