package org.kashiyatra.kashiyatra18;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    public static String storeUserDetails = "UserDetails";

    private final int DELAY = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                SharedPreferences preferences = getSharedPreferences(storeUserDetails, Context.MODE_PRIVATE);
                boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false);
                Intent mainIntent;
                if (isLoggedIn) {
                    mainIntent = new Intent(SplashActivity.this, HomeActivity.class);
                } else {
                    mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                }
                SplashActivity.this.startActivity(mainIntent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                SplashActivity.this.finish();
            }
        }, DELAY);
    }
}
