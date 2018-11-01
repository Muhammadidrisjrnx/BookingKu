package com.example.guru.bookingku;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class ControlClass extends Activity {
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        if (sharedPreferences.getString("user","").isEmpty()){
            Intent in=new Intent(getApplicationContext(),Main2Activity.class);
            startActivity(in);
            finish();
        }else{
            Intent in=new Intent(getApplicationContext(),Home.class);
            startActivity(in);
            finish();
        }
    }
}
