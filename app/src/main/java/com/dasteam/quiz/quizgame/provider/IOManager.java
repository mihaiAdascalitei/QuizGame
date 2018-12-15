package com.dasteam.quiz.quizgame.provider;

import android.content.Context;
import android.util.Log;

import com.dasteam.quiz.quizgame.model.player.PlayerModel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class IOManager {
    private static final String INTERNAL_PLAYER = "INTERNAL_PLAYER";
    private static IOManager INSTANCE;

    public static IOManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new IOManager();
        }
        return INSTANCE;
    }

    private IOManager(){}

    public void savePlayer(Object object) {
        try {
            FileOutputStream fos = QuizProvider.provideAppContext().openFileOutput(INTERNAL_PLAYER, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            Log.i(IOManager.class.getName(), "Object written successfully : " + object.toString());
            oos.close();
            fos.close();
        } catch (IOException e) {
            Log.e(IOManager.class.getName(), e.toString());
        }
    }

    public PlayerModel getPlayer() {

        try {
            FileInputStream fis = QuizProvider.provideAppContext().openFileInput(INTERNAL_PLAYER);
            ObjectInputStream ois;
            ois = new ObjectInputStream(fis);
            Object object = ois.readObject();

            if (object instanceof PlayerModel) {
                Log.i(IOManager.class.getName(), "Object retrieved successfully : " + object.toString());
                return (PlayerModel) object;
            }
        } catch (IOException | ClassNotFoundException e) {
            Log.e(IOManager.class.getName(), e.toString());
        }
        return null;
    }

    public void clear() {
        QuizProvider.provideAppContext().deleteFile(INTERNAL_PLAYER);
    }
}
