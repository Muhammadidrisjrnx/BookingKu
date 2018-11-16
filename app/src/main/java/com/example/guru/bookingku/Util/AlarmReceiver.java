package com.example.guru.bookingku.Util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.example.guru.bookingku.Activity.Main.MainActivity;
import com.example.guru.bookingku.R;

public class AlarmReceiver extends BroadcastReceiver {
    private SharedPreferences sharedPreferences;
    private static final String NOTIFICATION_CHANNEL_ID = "channel_id";
    private static final String NOTIFICATION_CHANNEL_NAME = "BookingKu";
    private static final int NOTIFICATION_ID = 987;
    private Intent startActivityIntent;
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        final String title = extras.getString("notification_title");
        final String desc = extras.getString("notification_content");

        sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userid",0);
        if(userId == 0){
            startActivityIntent = new Intent(context, ControlClass.class);
            Log.e("startactivity", "onReceive:  Control" );
        } else {
            startActivityIntent = new Intent(context, MainActivity.class);
            Log.e("startactivity", "onReceive:  MainActivity" );
        }
//        startActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, startActivityIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(desc)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentIntent(pendingIntent);
        Notification notification = notifBuilder.build();
        notification.flags = Notification.FLAG_NO_CLEAR | Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(NOTIFICATION_ID, notification);
    }
}
