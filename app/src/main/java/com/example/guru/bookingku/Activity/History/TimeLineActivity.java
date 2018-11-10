package com.example.guru.bookingku.Activity.History;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.guru.bookingku.Model.BookingResponse;
import com.example.guru.bookingku.Model.HistoryBooking;
import com.example.guru.bookingku.Network.BookingClient;
import com.example.guru.bookingku.Network.BookingService;
import com.example.guru.bookingku.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class TimeLineActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TimeLineAdapter mTimeLineAdapter;
    private List<HistoryBooking> mDataList = new ArrayList<>();
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "fetching data", Toast.LENGTH_SHORT).show();
        preferences = getSharedPreferences("login", MODE_PRIVATE);

        setContentView(R.layout.activity_timeline);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(getLinearLayoutManager());
        mRecyclerView.setHasFixedSize(true);
        mTimeLineAdapter = new TimeLineAdapter(mDataList);
        mRecyclerView.setAdapter(mTimeLineAdapter);

        int userId = preferences.getInt("userid", 0);
        Log.e("userID", "onCreate: " + userId);
        BookingService service = BookingClient.getRetrofit().create(BookingService.class);
        Call<BookingResponse> call = service.getHistoryBookingList(userId);
        call.enqueue(new Callback<BookingResponse>() {
            @Override
            public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
                try {
                    mDataList.addAll(response.body().getHistoryBookingList());
                    mTimeLineAdapter.notifyDataSetChanged();
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<BookingResponse> call, Throwable t) {

            }
        });
    }

    private LinearLayoutManager getLinearLayoutManager() {
        return new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
