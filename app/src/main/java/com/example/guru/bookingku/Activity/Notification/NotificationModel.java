package com.example.guru.bookingku.Activity.Notification;

public class NotificationModel {
    private String timeStamp;
    private String notificationText;

    public NotificationModel(String timeStamp, String notificationText) {
        this.timeStamp = timeStamp;
        this.notificationText = notificationText;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getNotificationText() {
        return notificationText;
    }
}
