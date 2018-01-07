package org.kashiyatra.ky18.services;

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

import org.kashiyatra.ky18.R;
import org.kashiyatra.ky18.SplashActivity;

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
        String title = remoteMessage.getData().get("title");
        String body = remoteMessage.getData().get("body");
        String link = remoteMessage.getData().get("link");
        Log.d(TAG, "Message Notification Title: " + title);
        Log.d(TAG, "Message Notification ImageUrl: " + imageUrl);
        Log.d(TAG, "Message Notification Body: " + body);
        Log.d(TAG, "Message Notification SubText: " + subText);
        Log.d(TAG, "Message Notification Link: " + link);


        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* request code */, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setColor(getColor(R.color.colorPrimaryDark))
                .setContentText(body)
                .setAutoCancel(false)
                .setContentIntent(pendingIntent)
                .setSound(defaultSoundUri);
        if (imageUrl != null) {
            try {
                URL url = new URL(imageUrl);
                Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                notificationBuilder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(image));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (subText != null) {
            notificationBuilder.setSubText(subText);
        }

        Intent visitIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));

        PendingIntent pendingIntentLink = PendingIntent.getActivity(this, 101, visitIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.addAction(R.drawable.ic_submit, "More", pendingIntentLink);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}