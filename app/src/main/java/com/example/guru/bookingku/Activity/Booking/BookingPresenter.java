package com.example.guru.bookingku.Activity.Booking;

import com.example.guru.bookingku.Activity.Base.PresenterActivity;

public class BookingPresenter implements PresenterActivity<BookingView> {

    BookingView view;

    @Override
    public void onAttach(BookingView view) {
        this.view = view;
    }

    @Override
    public void onDetach() {
        this.view = null;
    }

    public void onDateClicked(){
        view.showDatePickerDialog();
    }
    public void onTimeClicked(){
        view.showTimePickerDialog();
    }

    public void setDate(int year, int month, int dayOfMonth){
        String date = year + "/" + month + "/" + dayOfMonth;
        view.updateUI(0, date);
    }

    public void setTime(int hourOfDay, int minute){
        String time;
        if(hourOfDay < 10 && minute < 10)
            time = "0" + hourOfDay + ":" + "0" + minute;
        else if(hourOfDay < 10)
            time = "0" + hourOfDay + ":" + minute;
        else if(minute < 10)
            time = hourOfDay + ":" + "0" + minute;
        else
            time = hourOfDay + ":" + minute;
        view.updateUI(1, time);
    }

    public void onBookBtnClicked(){
        //send post request
        view.navigateToHomeActivity();
    }
}
