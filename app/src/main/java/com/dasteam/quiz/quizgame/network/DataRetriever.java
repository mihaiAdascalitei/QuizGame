package com.dasteam.quiz.quizgame.network;

public interface DataRetriever<T> {
    void onDataRetrieved(T data);

    void onDataFailed(String message, int code);
}
