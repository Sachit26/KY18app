package org.kashiyatra.ky18;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

import org.kashiyatra.ky18.adapters.SubeventsAdapter;
import org.kashiyatra.ky18.utils.RecyclerItemClickListener;

public class EventListActivity extends AppCompatActivity {
    private int eventPosition;
    private String mEventName;
    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        eventPosition = getIntent().getIntExtra("POSITION", 0);

        mEventName = getResources().getStringArray(R.array.event_names)[eventPosition];
        getSupportActionBar().setTitle(mEventName);

        mPrefs = getSharedPreferences(SplashActivity.storeUserDetails, Context.MODE_PRIVATE);

        String[] subeventNames;
        String[] subeventDescs;

        int bgImage = getResources().obtainTypedArray(R.array.event_backgrounds).getResourceId(eventPosition, -1);
        ImageView bgImageView = findViewById(R.id.background_image_view);
//        bgImageView.setImageResource(bgImage);
        switch (eventPosition) {
            case 0:
                subeventNames = getResources().getStringArray(R.array.bandish_subevent_names);
                subeventDescs = getResources().getStringArray(R.array.bandish_subevent_short_description);
                break;
            case 1:
                subeventNames = getResources().getStringArray(R.array.enquizta_subevent_names);
                subeventDescs = getResources().getStringArray(R.array.enquizta_subevent_short_description);
                break;
            case 2:
                subeventNames = getResources().getStringArray(R.array.mirage_subevent_names);
                subeventDescs = getResources().getStringArray(R.array.mirage_subevent_short_description);
                break;
            case 3:
                subeventNames = getResources().getStringArray(R.array.samwaad_subevent_names);
                subeventDescs = getResources().getStringArray(R.array.samwaad_subevent_short_description);
                break;
            case 4:
                subeventNames = getResources().getStringArray(R.array.abhinay_subevent_names);
                subeventDescs = getResources().getStringArray(R.array.abhinay_subevent_short_description);
                break;
            case 5:
                subeventNames = getResources().getStringArray(R.array.toolika_subevent_names);
                subeventDescs = getResources().getStringArray(R.array.toolika_subevent_short_description);
                break;
            case 6:
                subeventNames = getResources().getStringArray(R.array.natraj_subevent_names);
                subeventDescs = getResources().getStringArray(R.array.natraj_subevent_short_description);
                break;
            case 7:
                subeventNames = getResources().getStringArray(R.array.crosswindz_subevent_names);
                subeventDescs = getResources().getStringArray(R.array.crosswindz_subevent_short_description);
                break;
            case 8:
                subeventNames = getResources().getStringArray(R.array.xtasy_subevent_names);
                subeventDescs = getResources().getStringArray(R.array.xtasy_subevent_short_description);
                break;
            default:
                subeventNames = getResources().getStringArray(R.array.bandish_subevent_names);
                subeventDescs = getResources().getStringArray(R.array.bandish_subevent_short_description);
        }

        RecyclerView mSubeventRecycler = findViewById(R.id.subevent_list_recycler_view);
        RecyclerView.LayoutManager mSubeventLayoutManager = new LinearLayoutManager(this);
        mSubeventRecycler.setLayoutManager(mSubeventLayoutManager);
        RecyclerView.Adapter mSubeventsAdapter = new SubeventsAdapter(getApplicationContext(), subeventNames, subeventDescs);

        mSubeventRecycler.setAdapter(mSubeventsAdapter);
        mSubeventRecycler.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getApplicationContext(), EventActivity.class);
                        intent.putExtra("EVENT_POSITION", eventPosition);
                        intent.putExtra("SUBEVENT_POSITION", position);
                        startActivity(intent);
                    }
                })
        );


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.subevent_list_options, menu);
        if (mPrefs.getBoolean(mEventName, false)) {
            menu.findItem(R.id.action_subscribe).setVisible(false);
            menu.findItem(R.id.action_unsubscribe).setVisible(true);
        } else {
            menu.findItem(R.id.action_subscribe).setVisible(true);
            menu.findItem(R.id.action_unsubscribe).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (mPrefs.getBoolean(mEventName, false)) {
            menu.findItem(R.id.action_subscribe).setVisible(false);
            menu.findItem(R.id.action_unsubscribe).setVisible(true);
        } else {
            menu.findItem(R.id.action_subscribe).setVisible(true);
            menu.findItem(R.id.action_unsubscribe).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        int id = item.getItemId();
        switch (id) {
            case R.id.action_subscribe:
                FirebaseMessaging.getInstance().subscribeToTopic(mEventName);
                mEditor.putBoolean(mEventName, true);
                Toast.makeText(this, "Subscribed to notifications for " + mEventName, Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_unsubscribe:
                FirebaseMessaging.getInstance().unsubscribeFromTopic(mEventName);
                mEditor.putBoolean(mEventName, false);
                Toast.makeText(this, "Unsubscribed from notifications for " + mEventName, Toast.LENGTH_SHORT).show();
                break;
        }
        mEditor.commit();
        supportInvalidateOptionsMenu();
        return super.onOptionsItemSelected(item);
    }

}
