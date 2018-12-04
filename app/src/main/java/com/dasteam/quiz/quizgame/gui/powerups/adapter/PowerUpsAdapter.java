package com.dasteam.quiz.quizgame.gui.powerups.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.gui.powerups.ItemSellCallback;
import com.dasteam.quiz.quizgame.model.powerups.PowerUpsModel;
import com.dasteam.quiz.quizgame.utils.DrawableUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PowerUpsAdapter extends RecyclerView.Adapter<PowerUpsAdapter.PowerUpsHolder> {
    private List<PowerUpsModel> powers;
    private Context context;
    private ItemSellCallback callback;

    public PowerUpsAdapter(Context context) {
        this.context = context;
    }


    public void setData(List<PowerUpsModel> data) {
        powers = data;
        notifyDataSetChanged();
    }

    public void setCallback(ItemSellCallback callback) {
        this.callback = callback;
    }

    public void removeItem(PowerUpsModel power, int position) {
        powers.remove(power);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public PowerUpsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflater = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.power_ups_item, viewGroup, false);
        return new PowerUpsHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull PowerUpsHolder powerUpsHolder, int i) {
        powerUpsHolder.bind(powers.get(i));
    }

    @Override
    public int getItemCount() {
        return powers == null ? 0 : powers.size();
    }

    class PowerUpsHolder extends RecyclerView.ViewHolder {

        private TextView tvPowerName;
        private ImageView ivPowerIcon;
        private TextView tvSell;
        private TextView tvPowerCount;

        public PowerUpsHolder(@NonNull View itemView) {
            super(itemView);
            init(itemView);
        }

        private void init(View itemView) {
            tvPowerName = itemView.findViewById(R.id.tv_power_ups_item_name);
            ivPowerIcon = itemView.findViewById(R.id.iv_power_ups_item_icon);
            tvSell = itemView.findViewById(R.id.tv_power_ups_item_sell);
            tvPowerCount = itemView.findViewById(R.id.tv_power_up_count);
        }

        public void bind(PowerUpsModel power) {
            tvPowerName.setText(power.getPowerName());
            tvPowerCount.setText(power.getPowerCount());

            int icon = DrawableUtil.resIdByName(context, power.getPowerIconKey());
            Picasso.get().load(icon).into(ivPowerIcon);

            tvSell.setOnClickListener(v -> callback.onItemSell(power, getAdapterPosition()));

        }
    }
}
