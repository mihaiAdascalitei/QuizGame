package com.dasteam.quiz.quizgame.gui.quiz.variants.adpater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.model.question.AnswerModel;

import java.util.List;

public class VariantsAdapter extends RecyclerView.Adapter<VariantsAdapter.VariantsHolder> {

    private List<AnswerModel> answers;
    private VariantsItemListener callback;
    private int selectedPosition = -1;
    private Context context;

    public VariantsAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<AnswerModel> data) {
        answers = data;
        notifyDataSetChanged();
    }

    public void setCallback(VariantsItemListener callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public VariantsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_options_item, viewGroup, false);
        return new VariantsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VariantsHolder variantsHolder, int i) {
        variantsHolder.bind(answers.get(i));
    }

    @Override
    public int getItemCount() {
        return answers == null ? 0 : answers.size();
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void resetPosition() {
        selectedPosition = -1;
    }

    class VariantsHolder extends RecyclerView.ViewHolder {
        private TextView tvAnswer;

        public VariantsHolder(@NonNull View itemView) {
            super(itemView);
            tvAnswer = itemView.findViewById(R.id.tv_answer_text);
        }

        public void bind(AnswerModel answer) {
            tvAnswer.setText(answer.getName());
            itemView.setOnClickListener(v -> handleClickAction(answer));
            updateSelectedItems();
        }

        private void updateSelectedItems() {
            if (selectedPosition == getAdapterPosition()) {
                tvAnswer.setTextColor(ContextCompat.getColor(context, R.color.white));
                itemView.setBackground(ContextCompat.getDrawable(context, R.drawable.round_red_rectangle));
            } else {
                tvAnswer.setTextColor(ContextCompat.getColor(context, R.color.dark_green));
                itemView.setBackground(ContextCompat.getDrawable(context, R.drawable.round_white_rectangle));
            }
        }

        private void handleClickAction(AnswerModel answer) {
            selectedPosition = getAdapterPosition();
            callback.onItemClick(answer);
            notifyDataSetChanged();

        }
    }
}
