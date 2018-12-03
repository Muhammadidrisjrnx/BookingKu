package com.example.guru.bookingku.Activity.Detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.example.guru.bookingku.Activity.Booking.BookingActivity;
import com.example.guru.bookingku.Model.Item;
import com.example.guru.bookingku.R;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.scrolldown)
    ScrollView scrollView;
    @BindView(R.id.imageview_product)
    ImageView imageview_product;
    @BindView(R.id.bookNowBtn)
    Button bookNowBtn;
    @BindView(R.id.name_product)
    TextView name_product;
    @BindView(R.id.description_product)
    TextView description_product;
    @BindView(R.id.price_product)
    TextView price_product;
    @BindView(R.id.available_product)
    TextView available_product;
    Bundle bundlee;
    private boolean getAvailable;
    int idbarang=0;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setTitle("Detail produk");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        TextView txtnote2=(TextView)findViewById(R.id.txtnote2);
        txtnote2.setText(Html.fromHtml("&#8226;")+"makanan");



        ButterKnife.bind(this);


        Intent intent = getIntent();
        bundlee = intent.getExtras();
        if (bundlee != null) {
            idbarang = bundlee.getInt("id");
            Integer price = bundlee.getInt("price");
            Glide.with(getApplicationContext())
                    .load(bundlee.getString("image"))
                    .into(imageview_product);
            name_product.setText(bundlee.getString("name"));
            description_product.setText(bundlee.getString("description"));
            price_product.setText(price+"");
            available_product.setText(bundlee.getString("available"));
            getAvailable = bundlee.getBoolean("available");
            if(!getAvailable){
               bookNowBtn.setEnabled(false);
                bookNowBtn.setText("TIDAK TERSEDIA");
                bookNowBtn.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            }
        }
        bookNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(getApplicationContext(), BookingActivity.class);
                in.putExtra("orderid",idbarang);
                in.putExtra("order_nama", bundlee.getString("name"));
                startActivity(in);
            }
        });
    }
}
