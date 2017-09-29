package org.kashiyatra.kashiyatra18.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.kashiyatra.kashiyatra18.R;
import org.kashiyatra.kashiyatra18.utils.Faq;

import java.util.ArrayList;


public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.ViewHolder> {
    public ArrayList<Faq> mItems;

    public FaqAdapter(ArrayList<Faq> items) {
        mItems = items;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_faq, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.questionTextView.setText(mItems.get(position).getQuestion());
        holder.answerTextView.setText(mItems.get(position).getAnswer());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        TextView questionTextView, answerTextView;

        public ViewHolder(View v) {
            super(v);
            questionTextView = v.findViewById(R.id.question);
            answerTextView = v.findViewById(R.id.answer);
            mView = v;
        }
    }
}
