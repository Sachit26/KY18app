package org.kashiyatra.kashiyatra18.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.kashiyatra.kashiyatra18.R;
import org.kashiyatra.kashiyatra18.adapters.TeamAdapter;

public class TeamFragment extends Fragment {


    public TeamFragment() {
        // Required empty public constructor
    }


    public static TeamFragment newInstance() {
        TeamFragment fragment = new TeamFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_team, container, false);

        String[] names = getResources().getStringArray(R.array.team_names);
        String[] roles = getResources().getStringArray(R.array.team_roles);
        String[] photoUrls = getResources().getStringArray(R.array.team_photo_urls);
        String[] emails = getResources().getStringArray(R.array.team_emails);
        String[] fbLinks = getResources().getStringArray(R.array.team_fblinks);
        String[] linkedInLinks = getResources().getStringArray(R.array.team_linkedin_urls);

        RecyclerView mTeamRecyclerView = rootView.findViewById(R.id.team_recycler);
        RecyclerView.LayoutManager mTeamLayoutManager = new LinearLayoutManager(getActivity());
        RecyclerView.Adapter mTeamAdapter = new TeamAdapter(getContext(), names, roles, photoUrls, emails, fbLinks, linkedInLinks);

        mTeamRecyclerView.setLayoutManager(mTeamLayoutManager);
        mTeamRecyclerView.setAdapter(mTeamAdapter);

        return rootView;

    }

}
