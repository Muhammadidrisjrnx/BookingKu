package com.example.guru.bookingku.Activity.Jenisproduk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.guru.bookingku.Fragment.Home.adapter_list_item_spa;
import com.example.guru.bookingku.Fragment.Home.data_item_spa;
import com.example.guru.bookingku.Network.BookingClient;
import com.example.guru.bookingku.Network.BookingService;
import com.example.guru.bookingku.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class Massage extends AppCompatActivity {
    String data;
    public List<data_item_spa> arrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ShimmerFrameLayout mShimmerViewContainer;
    private SwipeRefreshLayout swipeRefreshLayout;
    private adapter_list_item_spa adapter;
    Call<List<data_item_spa>> call;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            data = extras.getString("category");
        }

        setTitle(data);
        mShimmerViewContainer = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        mShimmerViewContainer.startShimmerAnimation();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recycle_view_list);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                load_data();
            }
        });

        load_data();
    }

    private void load_data() {
        BookingService bookingService = BookingClient.getRetrofit().create(BookingService.class);

        if (data.equalsIgnoreCase("massage")){
            call = bookingService.dataProductmassage();
        }else if (data.equalsIgnoreCase("hair treadment")){
            call = bookingService.dataProducthair_treadment();
        }else if (data.equalsIgnoreCase("facial")){
            call = bookingService.dataProductfacial();
        }else if (data.equalsIgnoreCase("kuku")){
            call = bookingService.dataProductkuku();
        }

        call.enqueue(new Callback<List<data_item_spa>>() {
            @Override
            public void onResponse(Call<List<data_item_spa>> call, Response<List<data_item_spa>> response) {
                swipeRefreshLayout.setRefreshing(false);
                try {
                    arrayList.addAll(response.body());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter = new adapter_list_item_spa(getApplicationContext(), arrayList);
                    recyclerView.setAdapter(adapter);
                    Log.e("hasilnya", "onResponse: " + arrayList);
                    mShimmerViewContainer.stopShimmerAnimation();
                    mShimmerViewContainer.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<List<data_item_spa>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage());
                Toast.makeText(Massage.this, t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }
}
