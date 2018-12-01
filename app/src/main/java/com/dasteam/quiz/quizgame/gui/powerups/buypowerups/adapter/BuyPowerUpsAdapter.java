package com.dasteam.quiz.quizgame.gui.powerups.buypowerups.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dasteam.quiz.quizgame.R;
import com.dasteam.quiz.quizgame.model.powerups.PowerUpsModel;
import com.dasteam.quiz.quizgame.utils.DrawableUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BuyPowerUpsAdapter extends RecyclerView.Adapter<BuyPowerUpsAdapter.BuyPowerUpsHolder> {

    private List<PowerUpsModel> powers;
    private Context context;
    private ItemBuyCallback callback;
    private boolean playerHasPremium;

    public BuyPowerUpsAdapter(Context context, boolean playerHasPremium) {
        this.context = context;
        this.playerHasPremium = playerHasPremium;
    }

    public void setData(List<PowerUpsModel> data) {
        powers = data;
        notifyDataSetChanged();
    }


    public void setItemBuyCallback(ItemBuyCallback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public BuyPowerUpsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.buy_power_ups_item, viewGroup, false);
        return new BuyPowerUpsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyPowerUpsHolder buyPowerUpsHolder, int i) {
        buyPowerUpsHolder.bind(powers.get(i));
    }

    @Override
    public int getItemCount() {
        return powers == null ? 0 : powers.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class BuyPowerUpsHolder extends RecyclerView.ViewHolder {

        private TextView tvPowerUpTitle;
        private TextView tvPowerUpDescription;
        private TextView tvPowerUpPrice;
        private ImageView ivPowerUpIcon;
        private ConstraintLayout clBuyItem;

        public BuyPowerUpsHolder(@NonNull View itemView) {
            super(itemView);
            init(itemView);
        }

        private void init(View itemView) {
            tvPowerUpTitle = itemView.findViewById(R.id.tv_buy_power_ups_title);
            tvPowerUpDescription = itemView.findViewById(R.id.tv_buy_power_ups_description);
            tvPowerUpPrice = itemView.findViewById(R.id.tv_buy_power_ups_price);
            ivPowerUpIcon = itemView.findViewById(R.id.iv_buy_power_ups_icon);
            clBuyItem = itemView.findViewById(R.id.cl_buy_power_ups_price);
        }

        public void bind(PowerUpsModel power) {
            tvPowerUpTitle.setText(power.getPowerName());
            tvPowerUpDescription.setText(power.getPowerDescription());
            tvPowerUpPrice.setText(playerHasPremium ? context.getString(R.string.free_bonus) : power.getPowerPrice());
            int icon = DrawableUtil.resIdByName(context, power.getPowerIconKey());
            Picasso.get().load(icon).into(ivPowerUpIcon);

            clBuyItem.setOnClickListener(v -> callback.onItemBuy());
        }
    }
}
