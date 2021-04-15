package com.example.newsapllication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class NewsAdapter extends FirebaseRecyclerAdapter<News, NewsAdapter.ViewHolder> {





    public NewsAdapter(@NonNull FirebaseRecyclerOptions<News> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull News model) {
        holder.header.setText(model.getHeader());
        Glide.with(holder.image.getContext()).load(model.getImage()).into(holder.image);
        holder.image.setOnClickListener(view -> {
            InterstitialAd mInterstitialAd=new InterstitialAd(holder.image.getContext());
            mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
            MobileAds.initialize(holder.image.getContext(), "ca-app-pub-3940256099942544~3347511713");
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
            mInterstitialAd.setAdListener(new AdListener(){
                @Override
                public void onAdLoaded() {
                    if (mInterstitialAd.isLoaded()){
                        mInterstitialAd.show();
                    }else {
                        Log.d("TAG", "The interstitial wasn't loaded yet.");
                    }
                }

                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    Intent intent = new Intent(holder.cardView.getContext(), NewsDescription.class);
                    intent.putExtra("link_value", model.getLink());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    holder.cardView.getContext().startActivity(intent);

                }
            });
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow, parent, false);
        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView header;
        TextView link;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            header = itemView.findViewById(R.id.header);
            link = itemView.findViewById(R.id.link);
            cardView = itemView.findViewById(R.id.card);
        }
    }
}
