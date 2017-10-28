package org.kashiyatra.kashiyatra18;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.net.URL;


public class LoginActivity extends AppCompatActivity {
    private LoginButton fb_login_button;
    private Button reg_later_button;
    private CallbackManager callbackManager;
    private ProgressBar pb;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);

        fb_login_button = findViewById(R.id.fb_login_button);
        reg_later_button = findViewById(R.id.register_later);
        pb = findViewById(R.id.progress);
        pb.setVisibility(View.GONE);
        fb_login_button.setReadPermissions("email", "public_profile");

        fb_login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                fb_login_button.setEnabled(false);
                reg_later_button.setEnabled(false);
                GraphRequest request = GraphRequest.newMeRequest(
                        AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject jsonObject, GraphResponse response) {
                                try {
                                    pb.setVisibility(View.VISIBLE);
                                    new getDetails_fb().execute(jsonObject);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email, picture.width(150).height(150)");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(getApplicationContext(), "Please check internet connectivity", Toast.LENGTH_LONG).show();
            }
        });

        reg_later_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startHomeActivity();
            }
        });
    }

    public void startHomeActivity() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        LoginActivity.this.startActivity(intent);
        overridePendingTransition(R.anim.pull_right, R.anim.push_left);
        LoginActivity.this.finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        fb_login_button.setEnabled(true);
        reg_later_button.setEnabled(true);
    }

    private class getDetails_fb extends AsyncTask<JSONObject, Void, Void> {

        @Override
        protected Void doInBackground(JSONObject... params) {
            try {
                SharedPreferences prefs = getSharedPreferences(SplashActivity.storeUserDetails, MODE_PRIVATE);
                final SharedPreferences.Editor prefEditor = prefs.edit();
                JSONObject jsonObject = params[0];

                prefEditor.clear();

                String firstName = jsonObject.getString("first_name");
                String lastName = jsonObject.getString("last_name");
                String email = jsonObject.getString("email");

                String profilePicUrl = jsonObject.getJSONObject("picture").getJSONObject("data").getString("url");
                Bitmap profilePic = BitmapFactory.decodeStream(new URL(profilePicUrl).openConnection().getInputStream());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                profilePic.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] b = baos.toByteArray();
                String propic = Base64.encodeToString(b, Base64.DEFAULT);

                prefEditor.putString("profilePic", propic);
                prefEditor.putString("fullName", firstName + " " + lastName);
                prefEditor.putString("email", email);
                prefEditor.putBoolean("isLoggedIn", true);

                prefEditor.apply();

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Please try again", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            pb.setVisibility(View.GONE);
            startHomeActivity();
            finish();
        }
    }

}