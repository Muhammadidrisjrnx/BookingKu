package com.example.guru.bookingku.Fragment.Home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.guru.bookingku.Activity.Detail.DetailActivity;
import com.example.guru.bookingku.R;

import java.util.ArrayList;

public class adapter_list_item_spa extends RecyclerView.Adapter<adapter_list_item_spa.Holder> {

    private ArrayList<data_item_spa> arrayList;
    private Context context;

    public adapter_list_item_spa(Context context, ArrayList<data_item_spa> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_spa, parent, false);
        return new adapter_list_item_spa.Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        final data_item_spa data_item = arrayList.get(position);

        holder.textview_item_spa.setText(data_item.getName_item_product());
        holder.textview_description_item.setText(data_item.getDescription_item_product());
        holder.textview_cost_item.setText(data_item.getCost_item_product());
        Glide.with(context)
                .load(data_item.getImage_item_product())
                .into(holder.imageview_item_spa);
        holder.cardku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(),DetailActivity.class));
            }
        });
//        holder.button_item_spa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final String id_product = data_item.getId_item_product();
//                final String name_product = data_item.getName_item_product();
//                final String image_product = data_item.getImage_item_product();
//                final String description_product = data_item.getDescription_item_product();
//                final String cost_product = data_item.getCost_item_product();
//                Intent intent = new Intent(context.getApplicationContext(), details.class);
//                intent.putExtra("id_product", id_product);
//                intent.putExtra("name_product", name_product);
//                intent.putExtra("image_product", image_product);
//                intent.putExtra("description_product",description_product);
//                intent.putExtra("cost_product",cost_product);
//                context.startActivity(intent);
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ImageView imageview_item_spa;
        private CardView cardku;
        private TextView textview_item_spa,textview_description_item,textview_cost_item;
        private Button button_item_spa;

        public Holder(View itemView) {
            super(itemView);
            imageview_item_spa = (ImageView) itemView.findViewById(R.id.imageview_item_spa);
            cardku = (CardView) itemView.findViewById(R.id.cardku);
            textview_item_spa = (TextView) itemView.findViewById(R.id.textview_name_item);
            textview_description_item = (TextView)itemView.findViewById(R.id.textview_description_item);
            textview_cost_item = (TextView)itemView.findViewById(R.id.textview_cost_item);
        }
    }
}
