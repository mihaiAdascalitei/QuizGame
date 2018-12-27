package com.dasteam.quiz.quizgame.gui.quiz.endgame.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dasteam.quiz.quizgame.R;

import java.util.List;

public class EndGameAlsoAdapter extends RecyclerView.Adapter<EndGameAlsoAdapter.EndGameAlsoHolder> {
    private List<String> data;
    private AlsoItemListener callback;

    public void setCallback(AlsoItemListener callback) {
        this.callback = callback;
    }

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EndGameAlsoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feedback_category_item, viewGroup, false);
        return new EndGameAlsoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EndGameAlsoHolder endGameAlsoHolder, int i) {
        endGameAlsoHolder.bind(data.get(i));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class EndGameAlsoHolder extends RecyclerView.ViewHolder {

        private TextView tvItem;

        public EndGameAlsoHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tv_feedback_category_item);
        }

        public void bind(String item) {
            tvItem.setText(item);
            itemView.setOnClickListener(v -> callback.onItemClick(getAdapterPosition()));
        }
    }
}
