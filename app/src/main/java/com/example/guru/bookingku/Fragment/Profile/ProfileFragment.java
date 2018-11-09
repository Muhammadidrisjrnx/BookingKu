package com.example.guru.bookingku.Fragment.Profile;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.example.guru.bookingku.Fragment.Base.BaseFragment;
import com.example.guru.bookingku.Model.Profile;
import com.example.guru.bookingku.Network.BookingClient;
import com.example.guru.bookingku.Network.BookingService;
import com.example.guru.bookingku.R;
import com.example.guru.bookingku.Util.ControlClass;
import com.facebook.login.LoginManager;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment {
    private SharedPreferences sharedPreferences;
    CircleImageView profileimg;
    TextView profileName;
    TextView profileUsername;
    TextView tvlogout;
    @Override
    protected int getLayout() {
        return R.layout.fragment_profile;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferences = view.getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
        profileimg=view.findViewById(R.id.profileimg);
        profileName=view.findViewById(R.id.profileName);
        tvlogout=view.findViewById(R.id.tvlogout);
        profileUsername=view.findViewById(R.id.profileUsername);

        //
        BookingService bookingService = BookingClient.getRetrofit().create(BookingService.class);
        Call<Profile> call = bookingService.userprofile(sharedPreferences.getInt("userid",0));
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                profileName.setText(response.body().getEmail());
                profileUsername.setText(response.body().getName());
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                t.printStackTrace();
            }
        });
        //

        //profileName.setText(sharedPreferences.getString("name",""));
        //profileUsername.setText(sharedPreferences.getString("email",""));
        tvlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setMessage("Anda yakin ingin keluar ?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LoginManager.getInstance().logOut();
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.clear();
                                editor.commit();
                                Intent intent = new Intent(getActivity(), ControlClass.class);
                                getActivity().finish();
                                startActivity(intent);

                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        });

//        Glide.with(view.getContext())
//                .load(sharedPreferences.getString("avatar",""))
//                .centerCrop()
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .dontAnimate()
//                .into(profileimg);
        Log.e("Fragment", "onViewCreated: profile" );
    }
}