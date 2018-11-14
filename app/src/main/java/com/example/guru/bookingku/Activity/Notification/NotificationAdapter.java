package com.example.guru.bookingku.Activity.Notification;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guru.bookingku.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private ArrayList<NotificationModel> notificationList;

    public NotificationAdapter(ArrayList<NotificationModel> notificationList) {
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_notification, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        NotificationModel notification = notificationList.get(i);
        viewHolder.setElementValue(notification);
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTimeStamp;
        private TextView tvNotification;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNotification = itemView.findViewById(R.id.tv_notification);
            tvTimeStamp = itemView.findViewById(R.id.tv_timestamp);
        }

        private void setElementValue(NotificationModel notification){
            tvTimeStamp.setText(notification.getTimeStamp());
            tvNotification.setText(notification.getNotificationText());
        }
    }
}
