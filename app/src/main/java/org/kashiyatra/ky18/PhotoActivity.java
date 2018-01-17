package org.kashiyatra.ky18;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class PhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        int day = intent.getIntExtra("day", 1);
        int scheduleResourceId;
        switch (day) {
            case 2:
                scheduleResourceId = R.drawable.day2;
                getSupportActionBar().setTitle("Day 2");
                break;
            case 3:
                scheduleResourceId = R.drawable.day3;
                getSupportActionBar().setTitle("Day 3");
                break;
            default:
                scheduleResourceId = R.drawable.day1;
                getSupportActionBar().setTitle("Day 1");
        }

        ImageView photoView = findViewById(R.id.photo_view);
        photoView.setImageResource(scheduleResourceId);
    }
}
