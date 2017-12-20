package org.kashiyatra.kashiyatra18.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.kashiyatra.kashiyatra18.R;


public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.ViewHolder> {
    private String[] mQuestions;
    private String[] mAnswers;

    public FaqAdapter(String[] questions, String[] answers) {
        mQuestions = questions;
        mAnswers = answers;
    }

    @Override
    public int getItemCount() {
        return mQuestions.length;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_faq, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.questionTextView.setText(mQuestions[position]);
        holder.answerTextView.setText(mAnswers[position]);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView questionTextView, answerTextView;

        private ViewHolder(View v) {
            super(v);
            questionTextView = v.findViewById(R.id.question);
            answerTextView = v.findViewById(R.id.answer);
        }
    }
}
