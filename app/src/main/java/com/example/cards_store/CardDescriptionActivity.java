package com.example.cards_store;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class CardDescriptionActivity extends AppCompatActivity {
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_description);

        image = findViewById(R.id.iv_card_description_image);
        Glide.with(getApplicationContext()).load("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2015/09/greeting-card1.jpg").into(image);
    }
}
