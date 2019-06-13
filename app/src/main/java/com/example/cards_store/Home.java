package com.example.cards_store;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.ybq.android.spinkit.style.FadingCircle;

import java.util.ArrayList;

public class Home extends Fragment {
    private Context context;
    private ArrayList<String> images = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> descriptions = new ArrayList<>();
    private ArrayList<String> prices = new ArrayList<>();
    private ArrayList<String> qualities = new ArrayList<>();
    private TextView searchbar;
    private ImageView cart_button;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        // loading
        Dialog loadingDialog = new Dialog(getContext());
        loadingDialog.setContentView(R.layout.loading_dialog);
        ProgressBar progressBar = loadingDialog.findViewById(R.id.spin_kit);
        FadingCircle fadingCircle = new FadingCircle();
        progressBar.setIndeterminateDrawable(fadingCircle);
        loadingDialog.show();

        // search bar
        searchbar = view.findViewById(R.id.tv_search_bar);
        searchbar.setInputType(InputType.TYPE_NULL);

        //button
        cart_button = view.findViewById(R.id.img_home_fragment_cart_btn);
        cart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Cart.class));
                getActivity().overridePendingTransition(R.anim.bottom_top, R.anim.top_to_bottom);
            }
        });

        // add elements to recycle view
        addCards();
        context = getContext();
        RecyclerView cardsView = view.findViewById(R.id.rv_home_cards);
        CardsViewAdapter cardsViewAdapter = new CardsViewAdapter(context, images, names, descriptions, prices, qualities);
        cardsView.setAdapter(cardsViewAdapter);
        cardsView.setLayoutManager(new LinearLayoutManager(context));

        loadingDialog.hide();
        return view;
    }



    void addCards(){
        for(int i = 0; i < 10; i++ ){
            images.add("https://d22ir9aoo7cbf6.cloudfront.net/wp-content/uploads/sites/2/2015/09/greeting-card1.jpg");
            names.add("MONOYONO");
            descriptions.add("Hailing from Sweden, this popular stationery brand is best known for its lust-worthy collection of chic notebooks, calendars, home wares, and accessories that boast its signature Scandinavian design aesthetic.");
            prices.add("Rs.250.00");
            qualities.add("20");
        }
    }


}
