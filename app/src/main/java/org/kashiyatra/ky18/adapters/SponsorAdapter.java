package org.kashiyatra.ky18.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.kashiyatra.ky18.R;

/**
 * Created by HemanthSai on 31-Dec-17.
 */

public class SponsorAdapter extends RecyclerView.Adapter<SponsorAdapter.ViewHolder> {
    private String[] mSponsorNames, mSponsorTypes, mLogoUrls;
    private Context context;

    public SponsorAdapter(Context context, String[] names, String[] types, String[] logoUrls) {
        mSponsorNames = names;
        mSponsorTypes = types;
        mLogoUrls = logoUrls;
        this.context = context;
    }

    @Override
    public SponsorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sponsor_view, parent, false);
        return new SponsorAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SponsorAdapter.ViewHolder holder, final int position) {
        holder.mNameTextView.setText(mSponsorNames[position]);
        holder.mTypeTextView.setText(mSponsorTypes[position]);

        Glide.with(context)
                .load(mLogoUrls[position])
                .apply(new RequestOptions()
                        .centerCrop()
                        .dontAnimate()
                        .dontTransform())
                .into(holder.mLogoView);
    }

    @Override
    public int getItemCount() {
        return mSponsorNames.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public TextView mNameTextView, mTypeTextView;
        public ImageView mLogoView;

        public ViewHolder(View v) {
            super(v);
            mView = v;
            mNameTextView = v.findViewById(R.id.sponsor_name);
            mTypeTextView = v.findViewById(R.id.sponsor_type);
            mLogoView = v.findViewById(R.id.sponsor_image);
        }
    }
}