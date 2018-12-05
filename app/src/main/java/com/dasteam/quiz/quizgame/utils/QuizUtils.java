package com.dasteam.quiz.quizgame.utils;

public class QuizUtils {

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
}
