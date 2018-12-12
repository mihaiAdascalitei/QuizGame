package com.dasteam.quiz.quizgame.gui.lobby.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.model.player.PlayerModel;
import com.dasteam.quiz.quizgame.model.player.Ranking;

import java.util.List;


public class LobbyAdapter extends RecyclerView.Adapter<LobbyAdapter.LobbyHolder> {

    private final Context context;
    private List<PlayerModel> data;

    public LobbyAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public LobbyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflater = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lobby_player_item, viewGroup, false);
        return new LobbyHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull LobbyHolder lobbyHolder, int i) {
        lobbyHolder.bind(data.get(i));
    }

    public void setData(List<PlayerModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class LobbyHolder extends RecyclerView.ViewHolder {

        private TextView tvUsername;
        private ImageView ivRank;


        public LobbyHolder(@NonNull View itemView) {
            super(itemView);
            init(itemView);
        }

        private void init(View itemView) {
            tvUsername = itemView.findViewById(R.id.tv_item_lobby_player_name);
            ivRank = itemView.findViewById(R.id.iv_item_lobby_player_rank);
        }

        private void bind(PlayerModel player) {
            tvUsername.setText(player.getUsername());
//            setRankingData(player.getRanking());

        }

        private void setRankingData(Ranking rank) {
            switch (rank) {
                case TITAN:
                    Glide.with(context).load(R.drawable.ic_trophy_first).into(ivRank);
                    break;
                case WARRIOR:
                    Glide.with(context).load(R.drawable.ic_trophy_second).into(ivRank);
                    break;
                case HANDY:
                    Glide.with(context).load(R.drawable.ic_trophy_third).into(ivRank);
                    break;
                default:
                    Glide.with(context).load(R.drawable.ic_trophy_default).into(ivRank);
                    break;
            }
        }
    }
}
