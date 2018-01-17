package org.kashiyatra.ky18.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.kashiyatra.ky18.R;
import org.kashiyatra.ky18.adapters.SponsorAdapter;


public class SponsorsFragment extends Fragment {

    public SponsorsFragment() {
        // Required empty public constructor
    }


    public static SponsorsFragment newInstance() {
        SponsorsFragment fragment = new SponsorsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sponsors, container, false);

        String[] types = getResources().getStringArray(R.array.sponsor_types);
        String[] logoUrls = getResources().getStringArray(R.array.sponsor_logo_urls);

        RecyclerView mSponsorRecyclerView = rootView.findViewById(R.id.sponsor_recycler);
        GridLayoutManager mSponsorLayoutManager = new GridLayoutManager(getActivity(), 2);
        mSponsorLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position < 4 ? 2 : 1;
            }
        });
        RecyclerView.Adapter mSponsorAdapter = new SponsorAdapter(getContext(), types, logoUrls);

        mSponsorRecyclerView.setLayoutManager(mSponsorLayoutManager);
        mSponsorRecyclerView.setAdapter(mSponsorAdapter);

        return rootView;
    }

}
