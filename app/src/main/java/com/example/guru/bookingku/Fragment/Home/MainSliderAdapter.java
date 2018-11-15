package com.example.guru.bookingku.Fragment.Home;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

/**
 * @author S.Shahini
 * @since 2/12/18
 */

public class MainSliderAdapter extends SliderAdapter {

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {
        if (position==0){
            //viewHolder.bindImageSlide("https://assets.materialup.com/uploads/dcc07ea4-845a-463b-b5f0-4696574da5ed/preview.jpg");
            viewHolder.bindImageSlide("https://cf.beautyheaven-site-au.prod.bhn.net.au/sites/default/files/styles/heaven_fullsize/public/gallery/feature/diy-french-pedicure.jpg");
        }else if (position==1){
            //viewHolder.bindImageSlide("https://assets.materialup.com/uploads/20ded50d-cc85-4e72-9ce3-452671cf7a6d/preview.jpg");
            viewHolder.bindImageSlide("https://i.pinimg.com/originals/3a/ee/47/3aee47e9855ad047d7a3afbef71e4c86.jpg");
        }else if (position==2){
            //viewHolder.bindImageSlide("https://assets.materialup.com/uploads/76d63bbc-54a1-450a-a462-d90056be881b/preview.png");
            viewHolder.bindImageSlide("https://i.pinimg.com/564x/9d/df/1e/9ddf1ed43d2b33e69248ea1ecf6c634f.jpg");
        }
    }

}
