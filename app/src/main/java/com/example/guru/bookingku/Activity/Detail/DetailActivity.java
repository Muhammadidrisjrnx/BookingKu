package com.example.guru.bookingku.Activity.Detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.example.guru.bookingku.Activity.Booking.BookingActivity;
import com.example.guru.bookingku.Model.Item;
import com.example.guru.bookingku.R;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.bookNowBtn)
    Button bookNowBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Item item = extras.getParcelable("KEY_DETAIL");

        }

        Glide.with(getApplicationContext())
                .load("http://www.susannahnicholas.co.uk/image/data/AROMATHERAPY-FACIAL-sn.jpg")
                .into(ivImage);
        bookNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),BookingActivity.class));
            }
        });
    }
}
