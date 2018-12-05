package com.dasteam.quiz.quizgame;

import com.dasteam.quiz.quizgame.gui.login.ControllerCallback;
import com.dasteam.quiz.quizgame.gui.login.LoginController;
import com.dasteam.quiz.quizgame.gui.login.status.LoginResponseStatus;
import com.dasteam.quiz.quizgame.gui.premium.PremiumAccountController;
import com.dasteam.quiz.quizgame.gui.premium.PremiumCallback;
import com.dasteam.quiz.quizgame.gui.premium.status.PremiumValidateStatus;
import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.provider.QuizProvider;

import org.junit.Test;

import java.security.Provider;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ValidationUnitTest {

    @Test
    public void isLoginCorrect() {
        LoginController testController = new LoginController();
        String username = "testgood";
        String password = "testgood";

        testController.validateData(username, password, response -> assertEquals(response, LoginResponseStatus.SUCCESS));
    }

    @Test
    public void isCreditCardCorrect() {
        PremiumAccountController testController = new PremiumAccountController();
        String ccv = "123";
        String expDate = "12/20";
        String cardNumber = "1234567891234567";

        testController.validateData(cardNumber, ccv, expDate, status -> {
            assertEquals(status, PremiumValidateStatus.SUCCESS);
        });
    }

    @Test
    public void failCreditCard() {
        PremiumAccountController testController = new PremiumAccountController();
        String ccv = "12";
        String expDate = "12/20";
        String cardNumber = "12345";

        testController.validateData(cardNumber, ccv, expDate, status -> {
            assertEquals(status, PremiumValidateStatus.SUCCESS);
        });
    }


}