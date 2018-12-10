package com.dasteam.quiz.quizgame;

import android.content.Context;
import android.os.Looper;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.dasteam.quiz.quizgame.custom.LoadingDialog;
import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;
import com.dasteam.quiz.quizgame.provider.QuizProvider;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will requestExecute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class QuizInstrumentedTest {

    @Test
    public void playerProviderTest() {
        Object player = QuizProvider.provideIoManager().getPlayer();
        assertNotNull(player);
    }

    @Test
    public void loginHttpRequestTest() {
        String username = "zokkk";
        String password = "zokkk";

        QuizProvider.provideRepository().login(username, password, new DataRetriever<PlayerModel>() {
            @Override
            public void onDataRetrieved(PlayerModel data) {
                assertEquals(data.getUsername(), username);
            }

            @Override
            public void onDataFailed(String message, int code) {
                fail("Request failed");
            }
        });
    }

    @Test
    public void playerHasPremiumTest() {
        PlayerModel player = QuizProvider.provideIoManager().getPlayer();
        assertTrue(player.hasPremium());
    }

}
