package com.dasteam.quiz.quizgame.gui.premium.picker;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.dasteam.quiz.quizgame.R;

import java.util.Calendar;

public class DatePickerDialog extends Dialog {

    private TextView tvOk;
    private TextView tvCancel;
    private NumberPicker npMonth;
    private NumberPicker npYear;
    private DatePickerCallback callback;
    private String[] months = new String[12];
    private String[] years = new String[12];


    public DatePickerDialog(@NonNull Context context, DatePickerCallback callback) {
        super(context);
        this.callback = callback;
        setContentView(R.layout.exp_date_picker_dialog);
        setCancelable(false);
        init();
        initPickerData();
        setListeners();
    }

    private void init() {
        tvOk = findViewById(R.id.tv_exp_date_ok);
        tvCancel = findViewById(R.id.tv_exp_date_cancel);
        npMonth = findViewById(R.id.np_month);
        npYear = findViewById(R.id.np_year);
    }

    private void initPickerData() {
        initPickerMonth();
        initPickerYear();
    }

    private void setListeners() {
        tvCancel.setOnClickListener(v -> cancel());
        tvOk.setOnClickListener(v -> pick());
    }

    private void initPickerMonth() {
        npMonth.setMinValue(0);
        npMonth.setMaxValue(11);
        for (int i = 0; i < 12; i++) {
            months[i] = String.valueOf(i + 1);
        }
        npMonth.setDisplayedValues(months);
    }

    private void initPickerYear() {
        npYear.setMinValue(0);
        npYear.setMaxValue(11);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 0; i < 12; i++) {
            years[i] = String.valueOf(year + i);
        }

        npYear.setDisplayedValues(years);
    }

    private void pick() {
        String month = months[npMonth.getValue()];
        String year = years[npYear.getValue()];
        @SuppressLint("DefaultLocale")
        String selectedDate = String.format("%02d", Integer.valueOf(month)) + "/" + year.substring(2);
        callback.onOkSelected(selectedDate);
        cancel();
    }

}
