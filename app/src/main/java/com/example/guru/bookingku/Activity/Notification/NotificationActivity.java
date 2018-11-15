package com.example.guru.bookingku.Activity.Notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.guru.bookingku.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationActivity extends AppCompatActivity {

    private ArrayList<NotificationModel> notificationList = new ArrayList<>();
    private NotificationAdapter adapter;
    @BindView(R.id.recycler_view_notification)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);

        adapter = new NotificationAdapter(notificationList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
