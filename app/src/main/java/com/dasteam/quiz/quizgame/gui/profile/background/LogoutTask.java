package com.dasteam.quiz.quizgame.gui.profile.background;

import android.os.AsyncTask;

import com.dasteam.quiz.quizgame.provider.QuizProvider;

public class LogoutTask extends AsyncTask<Void, Void, Void> {

    private LogoutCallback callback;

    public LogoutTask(LogoutCallback callback) {
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        QuizProvider.provideIoManager().clear();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        callback.onLogoutFinished();
    }
}
