package org.kashiyatra.ky18;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class PhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        int day = intent.getIntExtra("day", 1);
        int placeholderId;
        String scheduleUrl;
        switch (day) {
            case 2:
                placeholderId = R.drawable.day2;
                scheduleUrl = "https://image.ibb.co/gfEZhR/day2.jpg";
                getSupportActionBar().setTitle("Day 2");
                break;
            case 3:
                placeholderId = R.drawable.day3;
                scheduleUrl = "https://image.ibb.co/hjH9Gm/day3.jpg";
                getSupportActionBar().setTitle("Day 3");
                break;
            default:
                placeholderId = R.drawable.day1;
                scheduleUrl = "https://image.ibb.co/iBP0NR/day1.jpg";
                getSupportActionBar().setTitle("Day 1");
        }

        ImageView photoView = findViewById(R.id.photo_view);

        Glide.with(this)
                .load(scheduleUrl)
                .apply(new RequestOptions()
                        .placeholder(placeholderId)
                        .error(placeholderId)
                        .fitCenter()
                        .dontAnimate()
                        .dontTransform())
                .into(photoView);
    }
}
