package org.kashiyatra.kashiyatra18.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.kashiyatra.kashiyatra18.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HelplineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HelplineFragment extends Fragment {

    public HelplineFragment() {
        // Required empty public constructor
    }

    public static HelplineFragment newInstance() {
        return new HelplineFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_helpline, container, false);
    }

}
