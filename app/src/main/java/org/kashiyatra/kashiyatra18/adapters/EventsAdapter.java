package org.kashiyatra.kashiyatra18.adapters;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.kashiyatra.kashiyatra18.R;


public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {
    private String[] mNames, mDescriptions;
    private Bitmap[] mLogos, mBackgrounds;

    public EventsAdapter(String[] names, String[] descriptions, Bitmap[] backgrounds, Bitmap[] logos) {
        mNames = names;
        mDescriptions = descriptions;
        mLogos = logos;
        mBackgrounds = backgrounds;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return mNames.length;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.titleTextView.setText(mNames[position]);
        holder.descriptionTextView.setText(mDescriptions[position]);
        holder.backgroundImageView.setImageBitmap(mBackgrounds[position]);
//        holder.logoImageView.setImageBitmap(mLogos[position]);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        TextView titleTextView, descriptionTextView;
        ImageView backgroundImageView, logoImageView;

        public ViewHolder(View v) {
            super(v);
            titleTextView = v.findViewById(R.id.event_title);
            descriptionTextView = v.findViewById(R.id.event_desc);
            backgroundImageView = v.findViewById(R.id.event_background);
//            logoImageView = v.findViewById(R.id.event_icon);
            mView = v;
        }
    }
}

