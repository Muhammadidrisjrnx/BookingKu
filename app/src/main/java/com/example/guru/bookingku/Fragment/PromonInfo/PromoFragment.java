package com.example.guru.bookingku.Fragment.PromonInfo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.guru.bookingku.Activity.Jenisproduk.Massage;
import com.example.guru.bookingku.Fragment.Base.BaseFragment;
import com.example.guru.bookingku.Fragment.Home.MainSliderAdapter;
import com.example.guru.bookingku.Fragment.Home.PicassoImageLoadingService;
import com.example.guru.bookingku.Fragment.Home.data_item_spa;
import com.example.guru.bookingku.Network.BookingClient;
import com.example.guru.bookingku.Network.BookingService;
import com.example.guru.bookingku.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ss.com.bannerslider.Slider;

import java.util.ArrayList;
import java.util.List;

public class PromoFragment extends BaseFragment {
    ArrayList<Gambar> arrayku;
    public RecyclerView recycler_view_list_film;
    public ArrayList<Time> listFilm = new ArrayList<>();
    public SectionListDataAdapter adapterAllTipe;
    private Slider slider;

    @Override
    protected int getLayout() {
        return R.layout.promoninfo;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Slider.init(new PicassoImageLoadingService(view.getContext()));

        arrayku=new ArrayList<Gambar>();

        recycler_view_list_film=(RecyclerView)view.findViewById(R.id.recycler_view_list_film2);


        Gambar g = new Gambar();
        g.setGambar("https://assets.materialup.com/uploads/dcc07ea4-845a-463b-b5f0-4696574da5ed/preview.jpg");
        arrayku.add(g);
        Gambar g2 = new Gambar();
        g2.setGambar("https://assets.materialup.com/uploads/20ded50d-cc85-4e72-9ce3-452671cf7a6d/preview.jpg");
        arrayku.add(g2);
        Gambar g3 = new Gambar();
        g3.setGambar("https://assets.materialup.com/uploads/76d63bbc-54a1-450a-a462-d90056be881b/preview.png");
        arrayku.add(g3);

        slider = view.findViewById(R.id.banner_slider1);

        slider.setSelectedSlideIndicator(ContextCompat.getDrawable(view.getContext(), R.drawable.selected_slide_indicator));
        slider.setUnSelectedSlideIndicator(ContextCompat.getDrawable(view.getContext(), R.drawable.unselected_slide_indicator));


        //delay for testing empty view functionality
        slider.postDelayed(new Runnable() {
            @Override
            public void run() {
                slider.setAdapter(new MainSliderAdapter(arrayku));
                slider.setSelectedSlide(0);
            }
        }, 1500);




        BookingService bookingService = BookingClient.getRetrofit().create(BookingService.class);

        Call<ResonWaktuFalse> call = bookingService.dataWaktu();
        call.enqueue(new Callback<ResonWaktuFalse>() {
            @Override
            public void onResponse(Call<ResonWaktuFalse> call, Response<ResonWaktuFalse> response) {
                try{
                    listFilm.addAll(response.body().getTime());
                    Toast.makeText(getActivity(), "fetch data success "+listFilm.size(), Toast.LENGTH_SHORT).show();

                    recycler_view_list_film.setHasFixedSize(true);
                    adapterAllTipe = new SectionListDataAdapter(getActivity(), listFilm);
                    recycler_view_list_film.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                    recycler_view_list_film.setAdapter(adapterAllTipe);
                    recycler_view_list_film.setNestedScrollingEnabled(false);
                    recycler_view_list_film.setVisibility(View.VISIBLE);
                }catch (Exception e){
                    Log.d("makan", "onResponse: "+e.toString());
                    Toast.makeText(getActivity(), "Something wrong is happen", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResonWaktuFalse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getActivity(), t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });

    }
}
