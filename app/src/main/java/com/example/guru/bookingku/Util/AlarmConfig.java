package com.example.guru.bookingku.Util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

public class AlarmConfig {

    private Context context;
    private String title, content;
    private int year, month, date, hour, minute;
    private static final int ALARM_REQUEST_CODE = 432;

    public AlarmConfig(Context context, String title, String content) {
        this.context = context;
        this.title = title;
        this.content = content;
    }

    public void setAlarm(int year, int month, int date, int hour, int minute) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.hour = hour;
        this.minute = minute;
        Log.e("setAlarm", "setAlarm: ");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(year, month, date, hour, minute);
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("notification_title", title);
        intent.putExtra("notification_content", content);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ALARM_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() - (AlarmManager.INTERVAL_HOUR * 2), pendingIntent);
    }
}
