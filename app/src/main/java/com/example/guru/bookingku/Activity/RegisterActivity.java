package com.example.guru.bookingku.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
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
    EditText inputUsername, inputEmail, inputPassword, inputConfirmPassword;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputUsername = findViewById(R.id.txtusername);
        inputEmail = findViewById(R.id.txtemail);
        inputPassword = findViewById(R.id.txtPassword);
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
                final String username = inputUsername.getText().toString().trim();
                final String password = inputPassword.getText().toString().trim();
                final String confirmPassword = inputConfirmPassword.getText().toString().trim();
                if(email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "please fill my heart first to send a request :(", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(!confirmPassword.equals(password)){
                        inputConfirmPassword.setError("lorem ipsum");
                    } else {
                        BookingService bookingService = BookingClient.getRetrofit().create(BookingService.class);
                        Call<Void> call = bookingService.signup(email, username, password);
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if(response.isSuccessful()){
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
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
            if(account.getPhotoUrl()!=null) {
                avatar = account.getPhotoUrl().toString();
            }else{
                avatar = null;
            }

            //intent
            Toast.makeText(getApplicationContext(),realName+" , "+username,Toast.LENGTH_LONG).show();
            editor = pref.edit();
            editor.putString("user",userId);
            editor.putString("name",realName);
            editor.putString("username",username);
            editor.putString("email",email);
            editor.putString("avatar",avatar);
            editor.commit();

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (ApiException ignored) {
        }
    }
}
