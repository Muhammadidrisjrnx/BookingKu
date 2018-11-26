package com.example.guru.bookingku.Util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class AlarmReceiver extends BroadcastReceiver {
    private static final String NOTIFICATION_CHANNEL_ID = "channel_id";
    private static final String NOTIFICATION_CHANNEL_NAME = "BookingKu";
    private static final int NOTIFICATION_ID = 987;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        final String title = "Bookingku";
        final String desc = extras.getString("Layanan anda akan segera dimulai dalam 2 jam lagi");

//        startActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        NotificationConfig notificationConfig = new NotificationConfig(context);
        notificationConfig.showNotification(title, desc, NOTIFICATION_ID, NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME);
    }
}
