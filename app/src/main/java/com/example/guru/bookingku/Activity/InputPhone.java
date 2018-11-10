package com.example.guru.bookingku.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.guru.bookingku.Activity.Main.MainActivity;
import com.example.guru.bookingku.Model.BookingResponse;
import com.example.guru.bookingku.Network.BookingClient;
import com.example.guru.bookingku.Network.BookingService;
import com.example.guru.bookingku.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputPhone extends AppCompatActivity {

    @BindView(R.id.inputPhone)
    EditText inputPhone;
    @BindView(R.id.submitBtn)
    Button submitBtn;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_phone);
        ButterKnife.bind(this);

        preferences = getSharedPreferences("login", MODE_PRIVATE);
        final int userId= preferences.getInt("userid", 0);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = inputPhone.getText().toString().trim();
                if(phone.equals(null)){
                    inputPhone.setError("Tolong jangan di kosongin ya");
                } else {
                    BookingService service = BookingClient.getRetrofit().create(BookingService.class);
                    Call<BookingResponse> call = service.insertPhone(userId, phone);
                    call.enqueue(new Callback<BookingResponse>() {
                        @Override
                        public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
                            try{
                                boolean success = response.body().getSuccess();
                                if(success){
                                    Intent intent = new Intent(InputPhone.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(InputPhone.this, "Something wrong is happen", Toast.LENGTH_SHORT).show();
                                }
                            }
                            catch (Exception e){}
                        }

                        @Override
                        public void onFailure(Call<BookingResponse> call, Throwable t) {
                            Toast.makeText(InputPhone.this, "Can't connect to server", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
