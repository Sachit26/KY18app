package org.kashiyatra.kashiyatra18.fragments;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.kashiyatra.kashiyatra18.EventListActivity;
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
        TypedArray backgrounds = getResources().obtainTypedArray(R.array.event_backgrounds);

        mEventRecycler = rootView.findViewById(R.id.eventlist_recycler_view);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        final int numColumns = 1 + displayMetrics.widthPixels * 160 / (displayMetrics.densityDpi * 200);
        final int numColumns = 2;
        mEventLayoutManager = new GridLayoutManager(getActivity(), numColumns);
        mEventRecycler.setLayoutManager(mEventLayoutManager);
        mEventRecycler.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewAdapterPosition();
                int padding = displayMetrics.densityDpi * 4 / 160;
                outRect.top = itemPosition < numColumns ? padding : padding / 2;
                outRect.left = itemPosition % numColumns == 0 ? padding : padding / 2;
                outRect.right = (itemPosition + 1) % numColumns == 0 ? padding : padding / 2;
                outRect.bottom = padding / 2;
            }
        });
        mEventAdapter = new EventsAdapter(getContext(), names, descriptions, backgrounds);
        mEventRecycler.setAdapter(mEventAdapter);
        mEventRecycler.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), EventListActivity.class);
                        intent.putExtra("POSITION", position);
                        startActivity(intent);
                    }
                })
        );
        return rootView;
    }

}

