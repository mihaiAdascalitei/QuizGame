package com.dasteam.quiz.quizgame.gui.quiz.closerange.adapter;

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

public class CloseRangeAdapter extends RecyclerView.Adapter<CloseRangeAdapter.CloseRangeHolder> {
    public static final int POSITION_REMOVE = 10;
    public static final int POSITION_ZERO = 9;

    private List<String> items;
    private RangeItemListener callback;
    private Context context;

    public void setData(List<String> data) {
        items = data;
        notifyDataSetChanged();
    }

    public void setCallback(RangeItemListener callback) {
        this.callback = callback;
    }

    public CloseRangeAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CloseRangeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.close_range_fragment_item, viewGroup, false);
        return new CloseRangeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CloseRangeHolder closeRangeHolder, int i) {
        closeRangeHolder.bind(items.get(i));
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    class CloseRangeHolder extends RecyclerView.ViewHolder {
        private TextView tvItem;

        public CloseRangeHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tv_close_range_text);
        }

        public void bind(String item) {
            if (getAdapterPosition() != POSITION_REMOVE) {
                tvItem.setText(item);
            } else {
                tvItem.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_backspace_white_24dp, 0);
            }
            itemView.setOnClickListener(v -> callback.onItemSelected(getAdapterPosition(), item));
        }
    }
}
