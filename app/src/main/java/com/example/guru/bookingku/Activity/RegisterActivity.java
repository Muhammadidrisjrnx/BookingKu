package com.example.guru.bookingku.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.guru.bookingku.Activity.History.RegisterRespon;
import com.example.guru.bookingku.Activity.Main.MainActivity;
import com.example.guru.bookingku.Network.BookingClient;
import com.example.guru.bookingku.Network.BookingService;
import com.example.guru.bookingku.R;
import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    SignInButton googleSignButton;
    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInOptions signInOptions;
    CallbackManager callbackManager;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    EditText inputUsername, inputEmail, inputPassword, inputConfirmPassword, noTelp;
    Button registerBtn;
    String Name;
    String phoneNo;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    private void getContactInfo(Intent data) {
// Check the SDK version and whether the permission is already granted or not.


        // TODO Auto-generated method stub

        ContentResolver cr = getContentResolver();


        Cursor cursor = managedQuery(data.getData(), null, null, null, null);
        while (cursor.moveToNext()) {
            String contactId = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.Contacts._ID));
            Name = cursor
                    .getString(cursor
                            .getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));

            String hasPhone = cursor
                    .getString(cursor
                            .getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

            Cursor emailCur = cr.query(
                    ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                    new String[]{contactId}, null);


            emailCur.close();


            if (hasPhone.equalsIgnoreCase("1"))
                hasPhone = "true";
            else
                hasPhone = "false";


            if (Boolean.parseBoolean(hasPhone)) {
                Cursor phones = getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                + " = " + contactId, null, null);
                while (phones.moveToNext()) {
                    phoneNo = phones
                            .getString(phones
                                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                }
                phones.close();
            }

            Log.d("hasil1", "getContactInfo: " + Name);
            //
            Log.d("hasil2", "getContactInfo: " + phoneNo);
            noTelp.setText(phoneNo);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        }
        inputUsername = findViewById(R.id.txtusername);
        inputEmail = findViewById(R.id.txtemail);
        inputPassword = findViewById(R.id.txtPassword);
        noTelp = findViewById(R.id.txtTelp);
        noTelp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (noTelp.getRight() - noTelp.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        //Toast.makeText(getApplicationContext(),"makan",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Intent.ACTION_PICK,
                                ContactsContract.Contacts.CONTENT_URI);
                        startActivityForResult(i, 1);
                        return true;
                    }
                }
                return false;
            }
        });

        inputConfirmPassword = findViewById(R.id.txtCPassword);
        pref = getSharedPreferences("login", MODE_PRIVATE);
        callbackManager = CallbackManager.Factory.create();
        signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(RegisterActivity.this, signInOptions);
        googleSignButton = findViewById(R.id.sign_in_button);
        googleSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(intent, 997);
            }
        });
        registerBtn = findViewById(R.id.btnDaftar);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = inputEmail.getText().toString().trim();
                final String telp = noTelp.getText().toString().trim();
                final String username = inputUsername.getText().toString().trim();
                final String password = inputPassword.getText().toString().trim();
                final String confirmPassword = inputConfirmPassword.getText().toString().trim();
                if (telp.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "please fill my heart first to send a request :(", Toast.LENGTH_SHORT).show();
                } else {
                    if (!confirmPassword.equals(password)) {
                        inputConfirmPassword.setError("password & confirm passsword don't match");
                    } else {
                        BookingService bookingService = BookingClient.getRetrofit().create(BookingService.class);
                        Call<RegisterRespon> call = bookingService.signup(email, username, password , telp);
                        call.enqueue(new Callback<RegisterRespon>() {
                            @Override
                            public void onResponse(Call<RegisterRespon> call, Response<RegisterRespon> response) {
                                try {
                                    if (response.isSuccessful()) {
                                        editor = pref.edit();
                                        editor.putInt("userid", response.body().getUserId());
                                        editor.apply();
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                } catch (Exception e){}

                            }

                            @Override
                            public void onFailure(Call<RegisterRespon> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });
                    }
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 997) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        } else if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                getContactInfo(data);

            }
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String realName = account.getDisplayName();
            String username = account.getGivenName() + account.getId();
            String email = account.getEmail();
            String userId = account.getId();
            String avatar;
            if (account.getPhotoUrl() != null) {
                avatar = account.getPhotoUrl().toString();
            } else {
                avatar = null;
            }

            //intent
            Toast.makeText(getApplicationContext(), realName + " , " + username, Toast.LENGTH_LONG).show();
            editor = pref.edit();
            editor.putString("user", userId);
            editor.putString("name", realName);
            editor.putString("username", username);
            editor.putString("email", email);
            editor.putString("avatar", avatar);
            editor.commit();

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (ApiException ignored) {
        }
    }
}
