package com.example.guru.bookingku.Fragment.PromonInfo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import com.example.guru.bookingku.Fragment.Base.BaseFragment;
import com.example.guru.bookingku.Fragment.Home.MainSliderAdapter;
import com.example.guru.bookingku.Fragment.Home.PicassoImageLoadingService;
import com.example.guru.bookingku.R;
import ss.com.bannerslider.Slider;

public class PromoFragment extends BaseFragment {
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
    }
}
