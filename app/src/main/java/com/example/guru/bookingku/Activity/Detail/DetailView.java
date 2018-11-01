package com.example.guru.bookingku.Activity.Detail;

import com.example.guru.bookingku.Model.Item;

public interface DetailView {
    void showDetailItem(Item item);
    void startBookingActivity(Item detailItem);
    void showError(String error);
}
