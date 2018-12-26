package com.dasteam.quiz.quizgame.gui.quiz.endgame.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.model.question.QuestionModel;

import java.util.List;

public class EndGameAnswerAdapter extends RecyclerView.Adapter<EndGameAnswerAdapter.EndGameAnswerHolder> {

    private List<QuestionModel> questions;

    public void setData(List<QuestionModel> data) {
        questions = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EndGameAnswerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.end_game_answer_item, viewGroup, false);
        return new EndGameAnswerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EndGameAnswerHolder endGameAnswerHolder, int i) {
        endGameAnswerHolder.bind(questions.get(i));
    }

    @Override
    public int getItemCount() {
        return questions == null ? 0 : questions.size();
    }

    public class EndGameAnswerHolder extends RecyclerView.ViewHolder {

        public EndGameAnswerHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(QuestionModel question) {

        }
    }
}
