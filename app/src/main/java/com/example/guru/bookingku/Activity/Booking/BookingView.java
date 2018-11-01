package com.example.guru.bookingku.Activity.Booking;

public interface BookingView {
    void showTimePickerDialog();
    void showDatePickerDialog();
    void updateUI(int Request, String format);
    void navigateToHomeActivity();
}
