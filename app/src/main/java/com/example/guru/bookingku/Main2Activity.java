package com.example.guru.bookingku;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.facebook.*;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class Main2Activity extends AppCompatActivity {

    private static final String EMAIL = "email";

    private LoginButton btnFacebook;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    EditText txtusername;
    EditText txtpassword;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        pref = getSharedPreferences("login", MODE_PRIVATE);
        callbackManager = CallbackManager.Factory.create();
        btnFacebook = findViewById(R.id.facebookSigninBtn);
        //cetakhash();
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();
        final GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(Main2Activity.this, signInOptions);
        SignInButton googleSignButton = findViewById(R.id.sign_in_button);
        googleSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(intent, 997);
            }
        });
        txtusername=(EditText)findViewById(R.id.txtusername);
        txtpassword=(EditText)findViewById(R.id.txtpassword);
        Button btnlogin=(Button)findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtusername.getText().toString().equalsIgnoreCase("aji") &&
                        txtpassword.getText().toString().equalsIgnoreCase("aji")){
                    editor = pref.edit();
                    editor.putString("user",txtusername.getText().toString());
                    editor.commit();

                    Intent in=new Intent(getApplicationContext(),Home.class);
                    startActivity(in);
                    finish();
                }
            }
        });
        btnFacebook.setReadPermissions(Arrays.asList(EMAIL));
        btnFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        String userDetil = response.getRawResponse();
                        try {
                            JSONObject jsonObject = new JSONObject(userDetil);
                            String userId = jsonObject.getString("id");
                            String realName = jsonObject.optString("name", "");
                            String username = jsonObject.optString("first_name", "") + jsonObject.optString("last_name", "") + jsonObject.getString("id");
                            String email = jsonObject.optString("email", "");
                            String avatar = "https://graph.facebook.com/" + userId + "/picture?type=large";

                            editor = pref.edit();
                            editor.putString("user",realName);
                            editor.commit();

                            Intent in=new Intent(getApplicationContext(),Home.class);
                            startActivity(in);
                            finish();

                            Toast.makeText(Main2Activity.this, email, Toast.LENGTH_SHORT).show();
                        } catch (JSONException ignored) { }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "name,email");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
                 }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

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
            editor.putString("user",email);
            editor.commit();
            Intent in=new Intent(getApplicationContext(),Home.class);
            startActivity(in);
        } catch (ApiException ignored) {
        }
    }
    /*
    public void cetakhash(){
        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("com.example.guru.bookingku", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
    }
    */
}
