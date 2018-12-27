package com.dasteam.quiz.quizgame.gui.quiz.endgame.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.model.question.AnswerModel;
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

        private TextView tvQuestion;
        private TextView tvGivenAnswer;
        private TextView tvRightAnswer;
        private ImageView ivCheckAnswer;

        public EndGameAnswerHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestion = itemView.findViewById(R.id.tv_end_item_question);
            tvGivenAnswer = itemView.findViewById(R.id.tv_end_item_answer);
            tvRightAnswer = itemView.findViewById(R.id.tv_end_right_answer);
            ivCheckAnswer = itemView.findViewById(R.id.iv_end_item_answer_check);
        }

        public void bind(QuestionModel question) {
            tvQuestion.setText(question.getQuestion());
            setAnswer(question.getAnswers());
        }

        private void setAnswer(List<AnswerModel> answers) {
            for (AnswerModel answer : answers) {
                if (answer.isSelected() && answer.isCorrect()) {
                    tvGivenAnswer.setText(answer.getName());
                    ivCheckAnswer.setBackgroundResource(R.drawable.ic_checked);
                    return;
                } else {
                    if (answer.isCorrect()) {
                        tvRightAnswer.setText(answer.getName());
                        ivCheckAnswer.setBackgroundResource(R.drawable.ic_cancel);
                    }
                    if (answer.isSelected()) {
                        tvGivenAnswer.setText(answer.getName());
                    }
                }
            }
        }
    }
}
