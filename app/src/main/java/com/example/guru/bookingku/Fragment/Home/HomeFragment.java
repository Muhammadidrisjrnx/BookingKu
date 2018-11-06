package com.example.guru.bookingku.Fragment.Home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.example.guru.bookingku.Fragment.Base.BaseFragment;
import com.example.guru.bookingku.R;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment {
    public ArrayList<data_item_spa> arrayList;
    public data_item_spa data_item;
    private RecyclerView recyclerView;
    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("Fragment", "onViewCreated: Home");
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view_list);
        arrayList = new ArrayList<data_item_spa>();
        load_data();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter_list_item_spa adapter = new adapter_list_item_spa(view.getContext(), arrayList);
        recyclerView.setAdapter(adapter);
    }
    private void load_data() {
        data_item = new data_item_spa();
        data_item.setId_item_product("aromaterapi_id");
        data_item.setName_item_product("Aromaterapi");
        data_item.setImage_item_product("https://nirvanabeauty.com.au/wp-content/uploads/Facial-image-1.jpg");
        data_item.setCost_item_product("2000");
        data_item.setDescription_item_product("DATA DESKRIPSI 1");
        arrayList.add(data_item);
        data_item = new data_item_spa();
        data_item.setId_item_product("lilin_pemanas_tungku_id");
        data_item.setName_item_product("Lilin Pemanas Tungku");
        data_item.setImage_item_product("https://img.grouponcdn.com/deal/3dqUDhfpWi7nkqpuZdE9w5abuf8P/3d-593x355/v1/c700x420.jpg");
        data_item.setCost_item_product("3000");
        data_item.setDescription_item_product("DATA DESKRIPSI 2");
        arrayList.add(data_item);
    }
}