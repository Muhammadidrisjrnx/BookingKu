package com.example.guru.bookingku;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.concurrent.TimeoutException;

public class slide_adapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public slide_adapter(Context context) {
        this.context = context;
    }

    public int[] image_slider = {R.drawable.wajah, R.mipmap.ic_launcher_round};
    public String[] name_slider = {"Face", "Slide 2"};
    public String[] description_slider = {"Lorem ipsum", "Lorem ipsum2"};

    @Override
    public int getCount() {
        return name_slider.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {

        return view == (RelativeLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.slide_layout, container, false);
        ImageView image_slide =(ImageView)view1.findViewById(R.id.image_slide);
        TextView text_view_slide = (TextView)view1.findViewById(R.id.text_view_slide);
        TextView text_view_description_slide = (TextView)view1.findViewById(R.id.text_view_description_slide);
        image_slide.setImageResource(image_slider[position]);
        text_view_slide.setText(name_slider[position]);
        text_view_description_slide.setText(description_slider[position]);
        container.addView(view1);
        return view1;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
        super.destroyItem(container, position, object);
    }
}
