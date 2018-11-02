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
import com.example.guru.bookingku.Activity.Booking.BookingActivity;
import com.example.guru.bookingku.Model.Item;
import com.example.guru.bookingku.R;

public class DetailActivity extends AppCompatActivity implements DetailView, View.OnClickListener {

    @BindView(R.id.addCartBtn)
    Button addCartBtn;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_item_name)
    TextView tvItemName;
    @BindView(R.id.tv_item_desc)
    TextView tvItemDesc;
    @BindView(R.id.bookNowBtn)
    Button bookNowBtn;

    final DetailPresenter presenter = new DetailPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Item item = extras.getParcelable("KEY_DETAIL");
            presenter.setItem(item);
        }
        addCartBtn.setOnClickListener(this);
        bookNowBtn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onAttach(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onDetach();
    }

    @Override
    public void showDetailItem(Item item) {
        tvItemName.setText(item.getName());
        tvItemDesc.setText(item.getDesc());
    }

    @Override
    public void startBookingActivity(Item item) {
        Intent intent = new Intent(this, BookingActivity.class);
        intent.putExtra("KEY_DETAIL", item);
        startActivity(intent);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, "Item: " + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (v == addCartBtn)
            presenter.onAddCartClicked();
        if (v == bookNowBtn)
            Toast.makeText(this, "Booked", Toast.LENGTH_SHORT).show(); //presenter.onBookNowClicked
    }
}
