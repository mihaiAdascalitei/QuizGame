package com.dasteam.quiz.quizgame.gui.feedback;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;
import com.dasteam.quiz.quizgame.gui.feedback.adapter.FeedbackAdapter;
import com.dasteam.quiz.quizgame.gui.feedback.adapter.FeedbackItemClickListener;
import com.dasteam.quiz.quizgame.gui.feedback.history.FeedbackHistoryActivity;
import com.dasteam.quiz.quizgame.gui.feedback.status.FeedbackResponseStatus;
import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;

import java.util.Arrays;

public class FeedbackActivity extends BaseActivity {

    public static final String FEEDBACK_PLAYER = "FEEDBACK_PLAYER";
    private FeedbackController feedbackController;
    private Button btnSend;
    private EditText etDescription;
    private RecyclerView rvCategory;
    private FeedbackAdapter adapter;
    private PlayerModel player;

    private String selectedCategory = null;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_keyboard_backspace);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getMenuInflater().inflate(R.menu.feedback_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.feedback_menu_history:
                openFeedbackHistory();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_feedback);
        configureToolbar(getString(R.string.settings_feedback));
        getExtraData();
        initAdapter();
        setAdapterData();
    }

    @Override
    protected void attachViews() {
        etDescription = findViewById(R.id.et_feedback_description);
        btnSend = findViewById(R.id.btn_send_feedback);
        rvCategory = findViewById(R.id.rv_feedback);
    }

    @Override
    protected void attachController() {
        feedbackController = new FeedbackController();
    }

    @Override
    protected void setListeners() {
        btnSend.setOnClickListener(v -> sendFeedback());
    }

    private void getExtraData() {
        player = (PlayerModel) getIntent().getSerializableExtra(FEEDBACK_PLAYER);
    }


    private void initAdapter() {
        adapter = new FeedbackAdapter(this);
        rvCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvCategory.setAdapter(adapter);
        adapter.setClickListener(name -> selectedCategory = name);
    }

    private void setAdapterData() {
        String[] feedbackCategories = getResources().getStringArray(R.array.feedback_categories);
        adapter.setData(Arrays.asList(feedbackCategories));
    }

    private void sendFeedback() {
        String description = etDescription.getText().toString();
        feedbackController.validateData(selectedCategory, description, this::handleFeedbackStatus);
    }

    private void handleFeedbackStatus(FeedbackResponseStatus status) {
        switch (status) {
            case SUCCESS:
                send();
                break;
            case CATEGORY_ERROR:
                showDelayedAlert(getString(R.string.category_error));
                break;
            case EMPTY_DESCRIPTION:
                showDelayedAlert(getString(R.string.empty_description_error));
                break;
            case LENGTH_ERROR:
                showDelayedAlert(getString(R.string.length_description_error));
                break;
            default:
                break;

        }
    }

    private void send() {
        String description = etDescription.getText().toString();
        showLoading(true);
        feedbackController.sendFeedback(player.getId(), selectedCategory, description, new DataRetriever<String>() {
            @Override
            public void onDataRetrieved(String data) {
                clearFeedbackEntries();
                showLoading(false);
                showSnackBar(findViewById(R.id.cl_main_feedback), getString(R.string.feedback_successfully_sent));
            }

            @Override
            public void onDataFailed(String message, int code) {
                showLoading(false);
                showAlert(getString(R.string.default_alert));
            }
        });
    }

    private void clearFeedbackEntries() {
        adapter.resetSelectedItems();
        selectedCategory = null;
        etDescription.setText("");
    }

    private void openFeedbackHistory() {
        startActivity(new Intent(this, FeedbackHistoryActivity.class).putExtra(FeedbackHistoryActivity.HISTORY_PLAYER, player));
    }
}
