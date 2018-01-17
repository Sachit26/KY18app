package org.kashiyatra.ky18.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.kashiyatra.ky18.PhotoActivity;
import org.kashiyatra.ky18.R;

public class ScheduleFragment extends Fragment {

    public ScheduleFragment() {
        // Required empty public constructor
    }


    public static ScheduleFragment newInstance() {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);

        CardView day1View = rootView.findViewById(R.id.day1_card);
        CardView day2View = rootView.findViewById(R.id.day2_card);
        CardView day3View = rootView.findViewById(R.id.day3_card);

        day1View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PhotoActivity.class);
                intent.putExtra("day", 1);
                startActivity(intent);
            }
        });

        day2View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PhotoActivity.class);
                intent.putExtra("day", 2);
                startActivity(intent);
            }
        });

        day3View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PhotoActivity.class);
                intent.putExtra("day", 3);
                startActivity(intent);
            }
        });
        return rootView;
    }


}
