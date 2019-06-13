package com.example.cards_store;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> images;
    private ArrayList<String> names;
    private ArrayList<String> extra_details;
    private ArrayList<String> prices;
    private ArrayList<String> qualities;
    private ArrayList<String> shipping;

    public CartItemAdapter(Context context, ArrayList<String> images, ArrayList<String> names, ArrayList<String> extra_details, ArrayList<String> prices, ArrayList<String> qualities, ArrayList<String> shipping){
        this.context = context;
        this.images = images;
        this.names = names;
        this.extra_details = extra_details;
        this.prices = prices;
        this.qualities = qualities;
        this.shipping = shipping;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_adapter_cart_items, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        Glide.with(context).load(images.get(i)).into(viewHolder.iv_image);
        viewHolder.tv_name.setText(names.get(i));
        viewHolder.tv_price.setText(prices.get(i));
        viewHolder.tv_extra_details.setText(extra_details.get(i));
        viewHolder.tv_quantity.setText(qualities.get(i));
        viewHolder.tv_shipping.setText(shipping.get(i));

        viewHolder.cardLayout.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CardDescriptionActivity.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(viewHolder.iv_image, "cardImage");

                try {
                    ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity)context, pairs);
                    context.startActivity(intent, activityOptions.toBundle());
                } catch (Exception i) {
                    Toast.makeText(context, "Not Worked" + i, Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_image;
        TextView tv_name;
        TextView tv_extra_details;
        TextView tv_price;
        TextView tv_quantity;
        TextView tv_shipping;
        ImageView btn_qnt_plus;
        ImageView btn_qnt_minus;
        ConstraintLayout cardLayout;
        RadioButton rb_item_select;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.iv_cart_item_image);
            tv_name = itemView.findViewById(R.id.tv_cart_item_name);
            tv_extra_details = itemView.findViewById(R.id.tv_cart_item_extra_details);
            tv_price = itemView.findViewById(R.id.tv_cart_item_price);
            cardLayout = itemView.findViewById(R.id.cart_adpater_item_layout);
            tv_quantity = itemView.findViewById(R.id.tv_cart_item_quantity);
            btn_qnt_plus = itemView.findViewById(R.id.btn_cart_item_qnt_plus);
            btn_qnt_minus = itemView.findViewById(R.id.btn_cart_item_qnt_minus);
            rb_item_select = itemView.findViewById(R.id.radio_cart_item_select);
            tv_shipping = itemView.findViewById(R.id.tv_cart_item_shipping);
        }
    }
}