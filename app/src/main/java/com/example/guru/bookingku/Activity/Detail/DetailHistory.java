package com.example.guru.bookingku.Activity.Detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.guru.bookingku.Model.BookingResponse;
import com.example.guru.bookingku.Network.BookingClient;
import com.example.guru.bookingku.Network.BookingService;
import com.example.guru.bookingku.R;
import retrofit2.Call;

public class DetailHistory extends AppCompatActivity {

    @BindView(R.id.tvProductName)
    TextView tvProductName;
    @BindView(R.id.tvProductDesc)
    TextView tvProductDesc;
    @BindView(R.id.ivProduct)
    ImageView ivProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history);
        ButterKnife.bind(this);

        BookingService service = BookingClient.getRetrofit().create(BookingService.class);
       // Call<BookingResponse>
    }
}
