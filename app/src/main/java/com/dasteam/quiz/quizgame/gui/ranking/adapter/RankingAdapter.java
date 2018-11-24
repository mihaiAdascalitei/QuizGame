package com.dasteam.quiz.quizgame.gui.ranking.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.model.LobbyPlayerModel;
import com.dasteam.quiz.quizgame.model.PlayerModel;

import java.util.List;

import static com.dasteam.quiz.quizgame.model.PlayerModel.RANK_FIRST;
import static com.dasteam.quiz.quizgame.model.PlayerModel.RANK_SECOND;
import static com.dasteam.quiz.quizgame.model.PlayerModel.RANK_THIRD;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.RankingHolder> {

    private Context context;
    private List<LobbyPlayerModel> players;

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

    public void setData(List<LobbyPlayerModel> data) {
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

        public void bind(LobbyPlayerModel player) {
            tvRankNumber.setText(String.valueOf(getAdapterPosition() + 1));
            tvRankName.setText(player.getUsername());
            setRankingData(player.getRank());
            setTopThree();
        }

        private void setRankingData(int rank) {
            switch (rank) {
                case RANK_FIRST:
                    Glide.with(context).load(R.drawable.ic_trophy_first).into(ivRankPicture);
                    break;
                case RANK_SECOND:
                    Glide.with(context).load(R.drawable.ic_trophy_second).into(ivRankPicture);
                    break;
                case RANK_THIRD:
                    Glide.with(context).load(R.drawable.ic_trophy_third).into(ivRankPicture);
                    break;
                default:
                    Glide.with(context).load(R.drawable.ic_trophy_default).into(ivRankPicture);
                    break;
            }

        }

        private void setTopThree() {
            int topThreeIconName = 0;
            int topThreeIconPlace = 0;
            switch (getAdapterPosition()) {
                case 0:
                    Glide.with(context).load(R.drawable.ic_crown).into(ivTopThree);
                    topThreeIconPlace = R.drawable.ic_crown;
                    topThreeIconName = R.drawable.ic_place_first;
                    tvRankNumber.setTextColor(ContextCompat.getColor(context, R.color.first_place));
                    break;
                case 1:
                    topThreeIconPlace = R.drawable.ic_bag;
                    topThreeIconName = R.drawable.ic_place_second;
                    tvRankNumber.setTextColor(ContextCompat.getColor(context, R.color.second_place));
                    break;
                case 2:
                    topThreeIconPlace = R.drawable.ic_scroll;
                    topThreeIconName = R.drawable.ic_place_third;
                    tvRankNumber.setTextColor(ContextCompat.getColor(context, R.color.third_place));
                    break;
                default:
                    ivTopThree.setVisibility(View.INVISIBLE);
                    break;
            }

            if (topThreeIconPlace != 0) {
                Glide.with(context).load(topThreeIconPlace).into(ivTopThree);
            }

            if (topThreeIconName != 0) {
                tvRankName.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, topThreeIconName), null, null, null);
            }
        }
    }
}
