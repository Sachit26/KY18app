package org.kashiyatra.kashiyatra18.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.kashiyatra.kashiyatra18.R;

/**
 * Created by Sachit on 06-01-2018.
 */

public class NotificationFragment extends Fragment {

    public NotificationFragment() {

    }

    public static NotificationFragment newInstance() {
        NotificationFragment fragment = new NotificationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notifications, container, false);
        return rootView;
    }
}
