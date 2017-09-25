package org.kashiyatra.kashiyatra18.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.kashiyatra.kashiyatra18.R;

public class UpdatesFragment extends Fragment {


    public UpdatesFragment() {
        // Required empty public constructor
    }


    public static UpdatesFragment newInstance() {
        UpdatesFragment fragment = new UpdatesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_updates, container, false);
    }
}
