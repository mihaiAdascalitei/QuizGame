package com.dasteam.quiz.quizgame.gui.quiz.options.adpater;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.model.question.AnswerModel;

import org.w3c.dom.Text;

import java.util.List;

public class VariantsAdapter extends RecyclerView.Adapter<VariantsAdapter.VariantsHolder> {

    private List<AnswerModel> answers;

    public void setData(List<AnswerModel> data) {
        answers = data;
        notifyDataSetChanged();
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

    class VariantsHolder extends RecyclerView.ViewHolder {
        private TextView tvAnswer;

        public VariantsHolder(@NonNull View itemView) {
            super(itemView);
            tvAnswer = itemView.findViewById(R.id.tv_answer_text);
        }

        public void bind(AnswerModel answer) {
            tvAnswer.setText(answer.getName());
        }
    }
}
