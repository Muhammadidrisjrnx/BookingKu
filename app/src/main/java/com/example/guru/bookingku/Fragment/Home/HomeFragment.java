package com.example.guru.bookingku.Fragment.Home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import com.example.guru.bookingku.Fragment.Base.BaseFragment;
import com.example.guru.bookingku.Network.BookingClient;
import com.example.guru.bookingku.Network.BookingService;
import com.example.guru.bookingku.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    public List<data_item_spa> arrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProgressBar pg;
    private SwipeRefreshLayout swipeRefreshLayout;
    private adapter_list_item_spa adapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycle_view_list);
        pg = view.findViewById(R.id.pgku);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new adapter_list_item_spa(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);
        load_data();
    }

    private void load_data() {
        BookingService bookingService = BookingClient.getRetrofit().create(BookingService.class);
        Call<List<data_item_spa>> call = bookingService.dataProduct();
        call.enqueue(new Callback<List<data_item_spa>>() {
            @Override
            public void onResponse(Call<List<data_item_spa>> call, Response<List<data_item_spa>> response) {
                swipeRefreshLayout.setRefreshing(false);
                try {
                    arrayList.clear();
                    adapter.notifyDataSetChanged();
                    arrayList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                    Log.e("TAG", "onResponse: " + arrayList);
                    pg.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<List<data_item_spa>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage());
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        load_data();
    }
}