package com.dasteam.quiz.quizgame.gui.ranking.adapter;

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


public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.RankingHolder> {

    private Context context;
    private List<PlayerModel> players;

    public RankingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RankingHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflater = LayoutInflater.from(context).inflate(R.layout.ranking_item, viewGroup, false);
        return new RankingHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull RankingHolder rankingHolder, int i) {
        rankingHolder.bind(players.get(i));
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return players == null ? 0 : players.size();
    }

    public void setData(List<PlayerModel> data) {
        players = data;
        notifyDataSetChanged();
    }

    class RankingHolder extends RecyclerView.ViewHolder {
        private TextView tvRankNumber;
        private TextView tvRankName;
        private ImageView ivRankPicture;
        private ImageView ivTopThree;

        public RankingHolder(@NonNull View itemView) {
            super(itemView);
            init(itemView);
        }

        private void init(View itemView) {
            tvRankName = itemView.findViewById(R.id.tv_ranking_player_name);
            tvRankNumber = itemView.findViewById(R.id.tv_ranking_number);
            ivRankPicture = itemView.findViewById(R.id.iv_ranking_player_rank);
            ivTopThree = itemView.findViewById(R.id.iv_ranking_top_three);
        }

        public void bind(PlayerModel player) {
            tvRankNumber.setText(String.valueOf(getAdapterPosition() + 1));
            tvRankName.setText(player.getUsername());
            setRankingData(player.getRanking());
            setTopThree();
        }

        private void setRankingData(Ranking rank) {
            switch (rank) {
                case TITAN:
                    Glide.with(context).load(R.drawable.ic_trophy_first).into(ivRankPicture);
                    break;
                case WARRIOR:
                    Glide.with(context).load(R.drawable.ic_trophy_second).into(ivRankPicture);
                    break;
                case HANDY:
                    Glide.with(context).load(R.drawable.ic_trophy_third).into(ivRankPicture);
                    break;
                default:
                    Glide.with(context).load(R.drawable.ic_trophy_default).into(ivRankPicture);
                    break;
            }

        }

        private void setTopThree() {
            int topThreeIconPlace = 0;
            switch (getAdapterPosition()) {
                case 0:
                    topThreeIconPlace = R.drawable.ic_crown;
                    break;
                case 1:
                    topThreeIconPlace = R.drawable.ic_bag;
                    break;
                case 2:
                    topThreeIconPlace = R.drawable.ic_scroll;
                    break;
                default:
                    ivTopThree.setVisibility(View.INVISIBLE);
                    break;
            }

            if (topThreeIconPlace != 0) {
                Glide.with(context).load(topThreeIconPlace).into(ivTopThree);
            }

        }
    }
}
