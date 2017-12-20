package org.kashiyatra.kashiyatra18.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.kashiyatra.kashiyatra18.R;

/**
 * Created by HemanthSai on 06-Dec-17.
 */

public class SubeventsAdapter extends RecyclerView.Adapter<SubeventsAdapter.ViewHolder> {
    private String[] mNames, mDescs;

    public SubeventsAdapter(String[] names, String[] descs) {
        mNames = names;
        mDescs = descs;
    }

    @Override
    public SubeventsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.subevent_view, parent, false);
        return new SubeventsAdapter.ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return mNames.length;
    }

    @Override
    public void onBindViewHolder(SubeventsAdapter.ViewHolder holder, int position) {

        holder.titleTextView.setText(mNames[position]);
        holder.descTextView.setText(mDescs[position]);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descTextView;
        private View mView;

        private ViewHolder(View v) {
            super(v);
            titleTextView = v.findViewById(R.id.subevent_title);
            descTextView = v.findViewById(R.id.subevent_desc);
            mView = v;
        }
    }
}
