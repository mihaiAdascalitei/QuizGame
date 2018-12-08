package com.dasteam.quiz.quizgame.gui.feedback.history.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.model.feedback.FeedbackModel;

import java.util.List;

public class FeedbackHistoryAdapter extends RecyclerView.Adapter<FeedbackHistoryAdapter.FeedbackHistoryHolder> {

    private List<FeedbackModel> feedbacks;
    private FeedbackHistoryItemClick callback;

    public void setData(List<FeedbackModel> data) {
        feedbacks = data;
        notifyDataSetChanged();
    }

    public void setCallback(FeedbackHistoryItemClick callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public FeedbackHistoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feedback_history_item, viewGroup, false);
        return new FeedbackHistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackHistoryHolder feedbackHolder, int i) {
        feedbackHolder.bind(feedbacks.get(i));
    }

    @Override
    public int getItemCount() {
        return feedbacks == null ? 0 : feedbacks.size();
    }

    public class FeedbackHistoryHolder extends RecyclerView.ViewHolder {
        private TextView tvCategory;
        private TextView tvDescription;
        private TextView tvDate;
        private ImageView ivDelete;

        public FeedbackHistoryHolder(@NonNull View itemView) {
            super(itemView);
            init(itemView);
        }

        private void init(View itemView) {
            tvCategory = itemView.findViewById(R.id.tv_feedback_history_category);
            tvDescription = itemView.findViewById(R.id.tv_feedback_history_description);
            tvDate = itemView.findViewById(R.id.tv_feedback_history_date);
            ivDelete = itemView.findViewById(R.id.iv_feedback_category_delete);
        }

        public void bind(FeedbackModel feedback) {
            tvDate.setText(feedback.getDateCreated());
            tvCategory.setText(feedback.getCategory());
            tvDescription.setText(feedback.getDescription());
            ivDelete.setOnClickListener(v -> callback.onItemClick(feedback));
        }
    }
}
