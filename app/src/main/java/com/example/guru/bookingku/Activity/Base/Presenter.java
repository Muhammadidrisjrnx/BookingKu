package com.example.guru.bookingku.Activity.Base;

public interface Presenter <T> {
    void onAttach(T view);
    void onDetach();
}
