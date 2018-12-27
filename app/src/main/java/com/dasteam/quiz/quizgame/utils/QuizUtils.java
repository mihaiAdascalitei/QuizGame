package com.dasteam.quiz.quizgame.utils;

import android.annotation.SuppressLint;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class QuizUtils {
    private final static long MILLISECS_PER_DAY = 24 * 60 * 60 * 1000;

    public static Integer integer(String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static String string(Integer value) {
        return String.valueOf(value);
    }

    public static boolean isEmpty(String value) {

        return value == null || value.equals("") || value.trim().equals("") || value.length() == 0;
    }

    @SuppressLint("SimpleDateFormat")
    public static String dateString(Date date) {
        return new SimpleDateFormat("dd MMM yyyy HH:mm").format(date);
    }

    public static Date date(String date) {
        @SuppressLint("SimpleDateFormat")
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public static int getDaysDifference(String premiumDate) {
        Date premium = date(premiumDate);
        Date currentDate = new Date();
        return Math.abs((int) (premium.getTime() / MILLISECS_PER_DAY) -
                (int) (currentDate.getTime() / MILLISECS_PER_DAY));
    }

    public static String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    public static <T> String saveArrayAsStringJson(List<T> data) {
        return new Gson().toJson(data);
    }

    public static <T> List<T> getArrayFromJsonString(String data, Type type) {
        return new Gson().fromJson(data, type);
    }
}
