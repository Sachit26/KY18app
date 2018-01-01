package org.kashiyatra.kashiyatra18.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.kashiyatra.kashiyatra18.R;

/**
 * Created by HemanthSai on 31-Dec-17.
 */

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {
    private String[] mNames, mRoles, mEmails, mFbLinks;
    private android.content.res.TypedArray mPhotos;
    private Context context;

    public TeamAdapter(Context context, String[] names, String[] roles, android.content.res.TypedArray photos, String[] emails, String[] fbLinks) {
        mNames = names;
        mRoles = roles;
        mPhotos = photos;
        mEmails = emails;
        mFbLinks = fbLinks;
        this.context = context;
    }

    @Override
    public TeamAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_view, parent, false);
        return new TeamAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TeamAdapter.ViewHolder holder, final int position) {
        holder.mNameTextView.setText(mNames[position]);
        holder.mRoleTextView.setText(mRoles[position]);
        holder.mPhotoView.setImageResource(mPhotos.getResourceId(position, -1));
        holder.mEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] email = new String[]{mEmails[position]};
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, email);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Query regarding Kashiyatra");
                context.startActivity(intent);
            }
        });
        holder.mFbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mFbLinks[position]));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNames.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public TextView mNameTextView, mRoleTextView;
        public de.hdodenhof.circleimageview.CircleImageView mPhotoView;
        public ImageButton mEmailButton, mFbButton;

        public ViewHolder(View v) {
            super(v);
            mView = v;
            mNameTextView = v.findViewById(R.id.name);
            mRoleTextView = v.findViewById(R.id.role);
            mPhotoView = v.findViewById(R.id.person_photo);
            mEmailButton = v.findViewById(R.id.send_email);
            mFbButton = v.findViewById(R.id.visit_fb);
        }
    }
}
