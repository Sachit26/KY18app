package org.kashiyatra.kashiyatra18.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.kashiyatra.kashiyatra18.EventActivity;
import org.kashiyatra.kashiyatra18.R;
import org.kashiyatra.kashiyatra18.adapters.EventsAdapter;
import org.kashiyatra.kashiyatra18.utils.RecyclerItemClickListener;

public class EventsFragment extends Fragment {

    public EventsFragment() {
        // Required empty public constructor
    }

    public static EventsFragment newInstance() {
        EventsFragment fragment = new EventsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_events, container, false);

        RecyclerView mEventRecycler;
        RecyclerView.Adapter mEventAdapter;
        RecyclerView.LayoutManager mEventLayoutManager;

        String[] names = getResources().getStringArray(R.array.event_names);
        String[] descriptions = getResources().getStringArray(R.array.event_descriptions);
        Bitmap backgrounds[] = {
                BitmapFactory.decodeResource(getResources(), R.drawable.mic),
                BitmapFactory.decodeResource(getResources(), R.drawable.mic),
                BitmapFactory.decodeResource(getResources(), R.drawable.mic),
                BitmapFactory.decodeResource(getResources(), R.drawable.mic),
                BitmapFactory.decodeResource(getResources(), R.drawable.mic),
                BitmapFactory.decodeResource(getResources(), R.drawable.mic),
                BitmapFactory.decodeResource(getResources(), R.drawable.mic),
                BitmapFactory.decodeResource(getResources(), R.drawable.mic)
        };

        Bitmap icons[] = {
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_trophy),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_trophy),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_trophy),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_trophy),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_trophy),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_trophy),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_trophy),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_trophy)
        };

        mEventRecycler = rootView.findViewById(R.id.eventlist_recycler_view);
        mEventLayoutManager = new GridLayoutManager(getActivity(), 2);
        mEventRecycler.setLayoutManager(mEventLayoutManager);
        mEventAdapter = new EventsAdapter(names, descriptions, backgrounds, icons);
        mEventRecycler.setAdapter(mEventAdapter);
        mEventRecycler.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.d("Clicked", "" + position);
                        Intent intent = new Intent(getActivity(), EventActivity.class);
                        intent.putExtra("POSITION", position);
                        startActivity(intent);
                    }
                })
        );
        return rootView;
    }

}

