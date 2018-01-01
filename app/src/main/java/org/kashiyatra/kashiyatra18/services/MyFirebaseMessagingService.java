package org.kashiyatra.kashiyatra18.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.kashiyatra.kashiyatra18.R;
import org.kashiyatra.kashiyatra18.SplashActivity;

import java.net.URL;

/**
 * Created by HemanthSai on 30-Dec-17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        String imageUrl = remoteMessage.getData().get("image_url");
        String subText = remoteMessage.getData().get("sub_text");

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            com.google.firebase.messaging.RemoteMessage.Notification notification = remoteMessage.getNotification();
            String title = notification.getTitle();
            String body = notification.getBody();
            String sound = notification.getSound();
            Log.d(TAG, "Message Notification Title: " + title);
            Log.d(TAG, "Message Notification ImageUrl: " + imageUrl);
            Log.d(TAG, "Message Notification Body: " + body);
            Log.d(TAG, "Message Notification Sound: " + sound);
            Log.d(TAG, "Message Notification SubText: " + subText);


            Intent intent = new Intent(this, SplashActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* request code */, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(notification.getTitle())
                    .setColor(getColor(R.color.colorAccent))
                    .setContentText(notification.getBody())
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);
            if (imageUrl != null) {
                try {
                    URL url = new URL(imageUrl);
                    Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    notificationBuilder.setLargeIcon(image);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (subText != null) {
                notificationBuilder.setSubText(subText);
            }
            if (true) {
                notificationBuilder.setSound(defaultSoundUri);
            }

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
        }
    }
}