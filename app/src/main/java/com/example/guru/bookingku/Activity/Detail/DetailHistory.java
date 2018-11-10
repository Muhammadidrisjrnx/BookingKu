package com.example.guru.bookingku.Activity.Detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.example.guru.bookingku.Model.HistoryBooking;
import com.example.guru.bookingku.R;

public class DetailHistory extends AppCompatActivity {

    @BindView(R.id.tvProductName)
    TextView tvProductName;
    @BindView(R.id.tvProductDesc)
    TextView tvProductDesc;
    @BindView(R.id.tvStatus)
    TextView tvStatus;

    private String productName;
    private String productDesc;
    private String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            HistoryBooking detailBooking = extras.getParcelable("history");
            productName = detailBooking.getOrder();
            productDesc = detailBooking.getOrderDesc();
            status = detailBooking.getStatus();

            tvProductName.setText(productName);
            tvProductDesc.setText(productDesc);
            tvStatus.setText(status.toUpperCase());

            if(status.equals("pending")){
                tvStatus.setTextColor(getResources().getColor(android.R.color.holo_orange_light));
            }
            if(status.equals("cancel")){
                tvStatus.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            }
            if (status.equals("done")){
                tvStatus.setTextColor(getResources().getColor(android.R.color.holo_green_light));
            }
        }



    }
}
