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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CardsViewAdapter extends RecyclerView.Adapter<CardsViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> images;
    private ArrayList<String> names;
    private ArrayList<String> descriptions;
    private ArrayList<String> prices;
    private ArrayList<String> qualities;

    public CardsViewAdapter(Context context, ArrayList<String> images, ArrayList<String> names, ArrayList<String> descriptions, ArrayList<String> prices, ArrayList<String> qualities){
        this.context = context;
        this.images = images;
        this.names = names;
        this.descriptions = descriptions;
        this.prices = prices;
        this.qualities = qualities;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_adapter_cards_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        Glide.with(context).load(images.get(i)).into(viewHolder.iv_image);
        viewHolder.tv_name.setText(names.get(i));
        viewHolder.tv_description.setText(descriptions.get(i));
        viewHolder.tv_quality.setText(qualities.get(i));
        viewHolder.tv_price.setText(prices.get(i));

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
        TextView tv_description;
        TextView tv_quality;
        TextView tv_price;
        ConstraintLayout cardLayout;
        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.iv_adapter_cards_view_image);
            tv_name = itemView.findViewById(R.id.iv_adapter_cards_view_name);
            tv_description = itemView.findViewById(R.id.iv_adapter_cards_view_description);
            tv_quality = itemView.findViewById(R.id.iv_adapter_cards_view_quality);
            tv_price = itemView.findViewById(R.id.iv_adapter_cards_view_price);
            cardLayout = itemView.findViewById(R.id.card_view_layout);
        }
    }
}
