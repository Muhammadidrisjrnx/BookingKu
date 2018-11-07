package com.example.guru.bookingku.Activity.Booking;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.guru.bookingku.R;

import java.util.ArrayList;

public class adapter_time_booking extends RecyclerView.Adapter<adapter_time_booking.Holder> {

    Context context;
    ArrayList<data_time>arrayList;

    public adapter_time_booking(Context context,ArrayList<data_time>arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_time, viewGroup, false);
        return new adapter_time_booking.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        final data_time data_time= arrayList.get(i);
        holder.textView.setText(data_time.getTime());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView textView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_view_time);
        }
    }
}
