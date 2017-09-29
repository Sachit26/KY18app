package org.kashiyatra.kashiyatra18.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.kashiyatra.kashiyatra18.R;
import org.kashiyatra.kashiyatra18.adapters.FaqAdapter;
import org.kashiyatra.kashiyatra18.utils.Faq;

import java.util.ArrayList;

public class FaqFragment extends Fragment {


    public FaqFragment() {

    }

    public static FaqFragment newInstance() {
        FaqFragment fragment = new FaqFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_faq, container, false);

        RecyclerView mFaqRecycler;
        RecyclerView.Adapter mFaqAdapter;
        RecyclerView.LayoutManager mFaqLayoutManager;

        ArrayList<Faq> mItems = new ArrayList<>();
        mItems.add(new Faq("Will you answer my question?", "Oh yes we will!"));
        mItems.add(new Faq("Will you answer my question?", "Oh yes we will!"));
        mItems.add(new Faq("Will you answer my question?", "Oh yes we will!"));
        mItems.add(new Faq("Will you answer my question?", "Oh yes we will!"));
        mItems.add(new Faq("Will you answer my question?", "Oh yes we will!"));
        mItems.add(new Faq("Will you answer my question?", "Oh yes we will!"));
        mItems.add(new Faq("Will you answer my question?", "Oh yes we will!"));
        mItems.add(new Faq("Will you answer my question?", "Oh yes we will!"));
        mItems.add(new Faq("Will you answer my question?", "Oh yes we will!"));
        mItems.add(new Faq("Will you answer my question?", "Oh yes we will!"));

        mFaqRecycler = rootView.findViewById(R.id.faq_recycler_view);
        mFaqLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mFaqRecycler.setLayoutManager(mFaqLayoutManager);
        mFaqAdapter = new FaqAdapter(mItems);
        mFaqRecycler.setAdapter(mFaqAdapter);

        return rootView;
    }
}