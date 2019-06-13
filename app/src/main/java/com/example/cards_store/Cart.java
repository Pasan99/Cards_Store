package com.example.cards_store;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.style.FadingCircle;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {
    private Context context;
    private ArrayList<String> images = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> extra_details = new ArrayList<>();
    private ArrayList<String> prices = new ArrayList<>();
    private ArrayList<String> qualities = new ArrayList<>();
    private ArrayList<String> shipping = new ArrayList<>();

    private ImageView close_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // loading
        Dialog loadingDialog = new Dialog(Cart.this);
        loadingDialog.setContentView(R.layout.loading_dialog);
        ProgressBar progressBar = loadingDialog.findViewById(R.id.spin_kit);
        FadingCircle fadingCircle = new FadingCircle();
        progressBar.setIndeterminateDrawable(fadingCircle);
        loadingDialog.show();

        //button
        close_btn = findViewById(R.id.iv_cart_close_btn);
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        context = Cart.this;
        addItems();
        startRecycleView();
        loadingDialog.hide();

    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    void addItems(){
            images.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2015/09/greeting-card1.jpg");
            names.add("MONOYONO");
            extra_details.add("Small, Black");
            prices.add("Rs.250.00");
            qualities.add("2");
            shipping.add("Free Shipping");

        }

    void startRecycleView(){
        RecyclerView cardsView = findViewById(R.id.rv_cart_items);
        CartItemAdapter cardsViewAdapter = new CartItemAdapter(context, images, names, extra_details, prices, qualities, shipping);
        cardsView.setAdapter(cardsViewAdapter);
        cardsView.setLayoutManager(new LinearLayoutManager(context));
    }
}
