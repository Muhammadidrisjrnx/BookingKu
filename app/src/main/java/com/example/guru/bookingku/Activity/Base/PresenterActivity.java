package com.example.guru.bookingku.Activity.Base;

public interface PresenterActivity<T> {
    void onAttach(T view);
    void onDetach();
}
