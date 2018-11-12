package com.dasteam.quiz.quizgame.network;

public interface DataRetriever<T> {
    void onDataRetrieved(T data, int code);

    void onDataFailed(Throwable throwable);
}
