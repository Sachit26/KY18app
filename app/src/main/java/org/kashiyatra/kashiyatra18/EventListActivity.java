package org.kashiyatra.kashiyatra18;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.kashiyatra.kashiyatra18.adapters.SubeventsAdapter;
import org.kashiyatra.kashiyatra18.utils.RecyclerItemClickListener;

public class EventListActivity extends AppCompatActivity {
    private int eventPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        eventPosition = getIntent().getIntExtra("POSITION", 0);

        getSupportActionBar().setTitle(getResources().getStringArray(R.array.event_names)[eventPosition]);

        String[] subeventNames;
        String[] subeventDescs;
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
            default:
                subeventNames = getResources().getStringArray(R.array.bandish_subevent_names);
                subeventDescs = getResources().getStringArray(R.array.bandish_subevent_short_description);
        }

        RecyclerView mSubeventRecycler = findViewById(R.id.subevent_list_recycler_view);
        RecyclerView.LayoutManager mSubeventLayoutManager = new LinearLayoutManager(this);
        mSubeventRecycler.setLayoutManager(mSubeventLayoutManager);
        RecyclerView.Adapter mSubeventsAdapter = new SubeventsAdapter(subeventNames, subeventDescs);

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
}
