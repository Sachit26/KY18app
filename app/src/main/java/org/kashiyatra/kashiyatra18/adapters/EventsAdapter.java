package org.kashiyatra.kashiyatra18.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.kashiyatra.kashiyatra18.R;


public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {
    private String[] mNames, mDescriptions;
    private TypedArray mBackgrounds;
    private Context context;

    public EventsAdapter(Context context, String[] names, String[] descriptions, TypedArray backgrounds) {
        mNames = names;
        mDescriptions = descriptions;
        mBackgrounds = backgrounds;
        this.context = context;
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
        holder.titleTextView.setTypeface(Typeface.createFromAsset(context.getAssets(), "HelveticaNeue-MediumCond.otf"));
        holder.descriptionTextView.setText(mDescriptions[position]);
        holder.backgroundImageView.setImageResource(mBackgrounds.getResourceId(position, -1));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        TextView titleTextView, descriptionTextView;
        ImageView backgroundImageView;

        public ViewHolder(View v) {
            super(v);
            titleTextView = v.findViewById(R.id.event_title);
            descriptionTextView = v.findViewById(R.id.event_desc);
            backgroundImageView = v.findViewById(R.id.event_background);
            mView = v;
        }
    }
}

