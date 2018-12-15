package com.dasteam.quiz.quizgame.gui.quiz.options.adpater;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dasteam.quiz.quizgame.R;

import java.util.List;

public class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.OptionsHolder> {

    private List<String> answers;

    public void setData(List<String> data) {
        answers = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OptionsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_options_item, viewGroup, false);
        return new OptionsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionsHolder optionsHolder, int i) {
        optionsHolder.bind(answers.get(i));
    }

    @Override
    public int getItemCount() {
        return answers == null ? 0 : answers.size();
    }

    class OptionsHolder extends RecyclerView.ViewHolder {

        public OptionsHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(String data) {

        }
    }
}
