package com.dasteam.quiz.quizgame.gui.quiz.base.manager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.base.BaseActivity;

public class FragmentNavigator {

    private FragmentManager fragmentManager;

    public FragmentNavigator(Context context) {
        fragmentManager = ((BaseActivity) context).getSupportFragmentManager();

    }

    public void replace(Fragment f) {
        fragmentManager.beginTransaction().replace(R.id.quiz_container, f).commit();
    }

    public void pop() {
        fragmentManager.popBackStack();
    }

    public int stackSize() {
        return fragmentManager.getBackStackEntryCount();
    }
}