package com.example.guru.bookingku.Activity.Booking;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.guru.bookingku.Activity.Main.MainActivity;
import com.example.guru.bookingku.Model.AvailableTime;
import com.example.guru.bookingku.Model.BookingResponse;
import com.example.guru.bookingku.Network.BookingClient;
import com.example.guru.bookingku.Network.BookingService;
import com.example.guru.bookingku.R;
import com.example.guru.bookingku.Util.AlarmConfig;
import com.example.guru.bookingku.Util.onItemClickListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends AppCompatActivity implements onItemClickListener {

    private static final String NOTIFICATION_TITLE = "Notifikasi Title";
    List<AvailableTime> availableTimeList = new ArrayList<>();
    private String selectedAvailableTime;
    private String selectedDate;
    private RecyclerView recyclerView;
    private adapter_time_booking adapter;
    private Button bookNowBtn;
    private SharedPreferences sharedPreferences;
    private TextView tvSelectedDateAndTime;
    private TextView txtavailable;
    private AlarmConfig alarmConfig;
    EditText txtdateku;
    Bundle bundlee;
    int orderid;
    private int year, month, date, hour, minute;

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
        setContentView(R.layout.activity_booking);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        String NOTIFICATION_CONTENT = "layanan " + extras.getString("order_name") + " akan dimulai 2 jam lagi";
        alarmConfig = new AlarmConfig(this, NOTIFICATION_TITLE, NOTIFICATION_CONTENT);
        Intent intent = getIntent();
        bundlee = intent.getExtras();
        if (bundlee != null) {
            orderid = bundlee.getInt("orderid");
        }

        tvSelectedDateAndTime = findViewById(R.id.selectedDateAndTime);
        txtavailable = findViewById(R.id.txtavailable);
        bookNowBtn = findViewById(R.id.bookNowBtn);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view_list_time);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new adapter_time_booking(this, availableTimeList);
        adapter.setOnItemClickListener(BookingActivity.this);
        recyclerView.setAdapter(adapter);
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        final int userid = sharedPreferences.getInt("userid", 0);
        txtdateku = (EditText) findViewById(R.id.txtdateku);
        txtdateku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getSupportFragmentManager(), "Date Picker");
            }
        });
        bookNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BookingActivity.this);
                builder.setTitle("Confirm booking ? ");
                builder.setMessage("Are you sure want to booking this service?");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        BookingService service = BookingClient.getRetrofit().create(BookingService.class);
                        final String date_string = selectedDate + " " + selectedAvailableTime;
                        Log.e("date", "onClick: " + date_string);
                        Call<BookingResponse> call = service.booking(userid, orderid, date_string);
                        call.enqueue(new Callback<BookingResponse>() {
                            @Override
                            public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
                                boolean success = response.body().getSuccess();
                                if(success){
                                    alarmConfig.setAlarm(year, month, date, hour, minute);
                                    Intent intent = new Intent(BookingActivity.this, MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(BookingActivity.this, "success", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(BookingActivity.this, "failed", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<BookingResponse> call, Throwable t) {
                                Toast.makeText(BookingActivity.this, "Can't Connect to Server", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void processDatePickerResult(int year, int month, int day) {
        adapter.row_index = -1;
        bookNowBtn.setEnabled(false);
        tvSelectedDateAndTime.setEnabled(false);
        tvSelectedDateAndTime.setText("");
        availableTimeList.clear();
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Fetching Available Time", Toast.LENGTH_SHORT).show();

        this.year = year;
        this.month = month;
        this.date = day;

        String year_string = String.valueOf(year);
        String month_string = String.valueOf(month + 1);
        String day_string = String.valueOf(day);

        selectedDate = year_string + "-" + month_string + "-" + day_string;
        txtdateku.setText(selectedDate);
        Toast.makeText(this, "Date Selected is : " + selectedDate, Toast.LENGTH_SHORT).show();
        BookingService service = BookingClient.getRetrofit().create(BookingService.class);
        Call<BookingResponse> call = service.getAvailableTimeList(selectedDate);
        call.enqueue(new Callback<BookingResponse>() {
            @Override
            public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
                try {
                    availableTimeList.addAll(response.body().getAvailableTime());
                    adapter.notifyDataSetChanged();
                    txtavailable.setVisibility(View.VISIBLE);
                } catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<BookingResponse> call, Throwable t) {
                Toast.makeText(BookingActivity.this, "Cannot connect to server", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onItemClick(int position) {
        tvSelectedDateAndTime.setEnabled(true);
        selectedAvailableTime = availableTimeList.get(position).getTime();
        String[] splitTime = selectedAvailableTime.split(":");
        hour = Integer.parseInt(splitTime[0]);
        minute = Integer.parseInt(splitTime[1]);
        bookNowBtn.setEnabled(true);
        Toast.makeText(this, selectedAvailableTime, Toast.LENGTH_SHORT).show();
        tvSelectedDateAndTime.setText(selectedDate + " " + selectedAvailableTime);
    }
}