package com.dasteam.quiz.quizgame.gui.feedback.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dasteam.quiz.quizgame.R;

import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.FeedbackHolder> {
    private List<String> categories;
    private FeedbackItemClickListener callback;
    private Context context;
    private int selectedPosition = -1;

    public FeedbackAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<String> data) {
        categories = data;
        notifyDataSetChanged();
    }

    public void setClickListener(FeedbackItemClickListener callback) {
        this.callback = callback;
    }

    public void resetSelectedItems() {
        selectedPosition = -1;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FeedbackHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feedback_category_item, viewGroup, false);
        return new FeedbackHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackHolder feedbackHolder, int i) {
        feedbackHolder.bind(categories.get(i));
    }

    @Override
    public int getItemCount() {
        return categories == null ? 0 : categories.size();
    }

    public class FeedbackHolder extends RecyclerView.ViewHolder {
        private TextView tvCategoryItem;

        public FeedbackHolder(@NonNull View itemView) {
            super(itemView);
            tvCategoryItem = itemView.findViewById(R.id.tv_feedback_category_item);
        }

        public void bind(String category) {
            tvCategoryItem.setText(category);
            itemView.setOnClickListener(v -> handleClickAction(category));
            updateSelectedItems();
        }

        private void updateSelectedItems() {
            if (selectedPosition == getAdapterPosition()) {
                tvCategoryItem.setTextColor(ContextCompat.getColor(context, R.color.white));
                itemView.setBackground(ContextCompat.getDrawable(context, R.drawable.round_red_rectangle));
            } else {
                tvCategoryItem.setTextColor(ContextCompat.getColor(context, R.color.dark_green));
                itemView.setBackground(ContextCompat.getDrawable(context, R.drawable.round_white_rectangle));
            }
        }

        private void handleClickAction(String category) {
            selectedPosition = getAdapterPosition();
            callback.onItemClick(category);
            notifyDataSetChanged();

        }

    }
}
