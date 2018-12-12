package com.dasteam.quiz.quizgame.network.call.player;

import com.dasteam.quiz.quizgame.model.player.PlayerModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RankingPlayersCall {
    @GET("/getRankingPlayers")
    Call<List<PlayerModel>> getRankingPlayers();
}
