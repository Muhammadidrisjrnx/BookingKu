package com.example.guru.bookingku.Fragment.PromonInfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.guru.bookingku.R;

import java.util.ArrayList;

public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder> {

    private View view;
    ArrayList<ResonWaktuFalse> articleFilm;
    Context context;

    public SectionListDataAdapter(Context context, ArrayList<ResonWaktuFalse> articleFilm) {
        Log.d("di adapter", "SectionListDataAdapter: "+articleFilm.size());
        this.articleFilm=articleFilm;
        this.context=context;
    }

    @SuppressLint("InflateParams")
    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_single_card, viewGroup,false);
        SingleItemRowHolder rowHolder = new SingleItemRowHolder(view);
        return rowHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull final SectionListDataAdapter.SingleItemRowHolder holder, final int position) {

        final ResonWaktuFalse singleItem = articleFilm.get(position);

    }

    @Override
    public int getItemCount() {
        return articleFilm.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private ImageView ivposter;
        private TextView release_date;
        private CardView cardku;

        private SingleItemRowHolder(final View view) {
            super(view);
            this.tvTitle = (TextView)view.findViewById(R.id.tvjudul);
            this.ivposter = (ImageView)view.findViewById(R.id.ivcover);
            this.release_date = (TextView)view.findViewById(R.id.release_date);
            this.cardku = (CardView) view.findViewById(R.id.cardku);


        }

    }
}
