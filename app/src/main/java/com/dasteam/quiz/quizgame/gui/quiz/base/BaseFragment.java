package com.dasteam.quiz.quizgame.gui.quiz.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.dasteam.quiz.quizgame.gui.quiz.base.manager.FragmentNavigator;

public class BaseFragment extends Fragment {

    private FragmentNavigator navigator;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigator = new FragmentNavigator(this.getContext());
    }

    protected FragmentNavigator fragmentNavigator() {
        return this.navigator;
    }

}
