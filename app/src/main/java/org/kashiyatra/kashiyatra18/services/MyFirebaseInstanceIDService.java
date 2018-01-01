package org.kashiyatra.kashiyatra18.services;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import org.kashiyatra.kashiyatra18.SplashActivity;

/**
 * Created by HemanthSai on 30-Dec-17.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = MyFirebaseInstanceIDService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        // Saving reg id to shared preferences
        Log.e(TAG, "refreshed token: " + refreshedToken);
        storeTokenInPref(refreshedToken);

        // sending reg id to your server
        sendRegistrationToServer(refreshedToken);

        // Notify UI that registration has completed, so the progress indicator can be hidden.
    }

    private void sendRegistrationToServer(final String token) {
        // sending gcm token to server
        Log.e(TAG, "sendRegistrationToServer: " + token);
    }

    private void storeTokenInPref(String token) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(SplashActivity.storeUserDetails, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("firebase_token", token);
        editor.commit();
    }
}