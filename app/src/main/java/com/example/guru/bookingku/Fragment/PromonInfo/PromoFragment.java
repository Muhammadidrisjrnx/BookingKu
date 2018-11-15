package com.example.guru.bookingku.Fragment.PromonInfo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.guru.bookingku.Fragment.Base.BaseFragment;
import com.example.guru.bookingku.Fragment.Home.MainSliderAdapter;
import com.example.guru.bookingku.Fragment.Home.PicassoImageLoadingService;
import com.example.guru.bookingku.R;
import ss.com.bannerslider.Slider;

import java.util.ArrayList;

public class PromoFragment extends BaseFragment {
    public RecyclerView recycler_view_list_film;
    public ArrayList<Film> listFilm = new ArrayList<>();
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

        slider = view.findViewById(R.id.banner_slider1);

        slider.setSelectedSlideIndicator(ContextCompat.getDrawable(view.getContext(), R.drawable.selected_slide_indicator));
        slider.setUnSelectedSlideIndicator(ContextCompat.getDrawable(view.getContext(), R.drawable.unselected_slide_indicator));


        //delay for testing empty view functionality
        slider.postDelayed(new Runnable() {
            @Override
            public void run() {
                slider.setAdapter(new MainSliderAdapter());
                slider.setSelectedSlide(0);
            }
        }, 1500);

        Film f1=new Film();
        f1.setId(1);
        f1.setTitle("information");
        f1.setRelease_date("makanan");
        listFilm.add(f1);

        recycler_view_list_film = (RecyclerView) view.findViewById(R.id.recycler_view_list_film2);
        recycler_view_list_film.setHasFixedSize(true);
        adapterAllTipe = new SectionListDataAdapter(view.getContext(), listFilm);
        recycler_view_list_film.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        recycler_view_list_film.setAdapter(adapterAllTipe);
        recycler_view_list_film.setNestedScrollingEnabled(false);
        recycler_view_list_film.setVisibility(View.VISIBLE);
    }
}
