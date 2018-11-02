package com.example.guru.bookingku.Activity.Booking;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.guru.bookingku.Activity.Detail.DetailActivity;
import com.example.guru.bookingku.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookingActivity extends AppCompatActivity implements BookingView, View.OnClickListener{
    private static final int UPDATE_TV_DATE_REQUEST = 0;
    private static final int UPDATE_TV_TIME_REQUEST = 1;

    @BindView(R.id.tv_date) EditText tvDate;
    @BindView(R.id.tv_time) EditText tvTime;
    @BindView(R.id.bookNowBtn) Button bookBtn;
    final BookingPresenter presenter = new BookingPresenter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ButterKnife.bind(this);
        tvDate.setOnClickListener(this);
        tvTime.setOnClickListener(this);
        bookBtn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onAttach(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onDetach();
    }

    @Override
    public void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                presenter.setTime(hourOfDay, minute);
            }
        }, hour, minute, true);
        String title = getResources().getString(R.string.timepicker_title);
        timePickerDialog.setTitle(title);
        timePickerDialog.show();
    }

    @Override
    public void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                presenter.setDate(year, month, dayOfMonth);
            }
        }, year, month, day);
        String title = getResources().getString(R.string.datepicker_title);
        datePickerDialog.setTitle(title);
        datePickerDialog.show();
    }

    @Override
    public void updateUI(int request, String format) {
        switch (request){
            case UPDATE_TV_DATE_REQUEST: tvDate.setText(format);
            break;
            case UPDATE_TV_TIME_REQUEST: tvTime.setText(format);
            break;
        }
    }

    @Override
    public void navigateToHomeActivity() {
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if(v == tvDate)
            presenter.onDateClicked();
        if(v == tvTime)
            presenter.onTimeClicked();
        if(v == bookBtn)
            presenter.onBookBtnClicked();
    }


}
