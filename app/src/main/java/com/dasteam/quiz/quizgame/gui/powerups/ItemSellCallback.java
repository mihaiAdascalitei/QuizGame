package com.dasteam.quiz.quizgame.gui.powerups;

import com.dasteam.quiz.quizgame.model.powerups.PowerUpsModel;

public interface ItemSellCallback {
    void onItemSell(PowerUpsModel power, int position);
}
