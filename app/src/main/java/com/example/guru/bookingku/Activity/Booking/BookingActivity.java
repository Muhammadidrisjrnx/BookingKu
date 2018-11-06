package com.example.guru.bookingku.Activity.Booking;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.*;

import com.example.guru.bookingku.Activity.Detail.DetailActivity;
import com.example.guru.bookingku.R;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookingActivity extends AppCompatActivity{
    public ArrayList<data_time>arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);



//        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycle_view_list_time);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        adapter_time_booking adapter = new adapter_time_booking(getApplicationContext(),arrayList);
//        recyclerView.setAdapter(adapter);


        EditText txtdateku=(EditText)findViewById(R.id.txtdateku);
        txtdateku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getSupportFragmentManager(), "Date Picker");
            }
        });
    }

    public void processDatePickerResult(int year, int month, int day) {

        String year_string = String.valueOf(year);
        String month_string = String.valueOf(month + 1);
        String day_string = String.valueOf(day);

        String date_message = day_string + "/" + month_string + "/" + year_string;
        Toast.makeText(this, "Date Selected is : " + date_message, Toast.LENGTH_SHORT).show();

    }
}