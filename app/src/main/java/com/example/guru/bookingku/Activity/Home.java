package com.example.guru.bookingku.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.guru.bookingku.R;
import com.example.guru.bookingku.Util.ControlClass;

public class Home extends AppCompatActivity {
    //untuk list produk

    private SharedPreferences sharedPreferences;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        TextView tv =(TextView)findViewById(R.id.usernamelogin);
        tv.setText(sharedPreferences.getString("user",""));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        // return true so that the menu pop up is opened
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_game:
                SharedPreferences preferences = getSharedPreferences("login", 0);
                preferences.edit().remove("user").commit();
                this.finish();
                Intent in=new Intent(getApplicationContext(),ControlClass.class);
                startActivity(in);
            break;
        }
        return true;
    }
}
