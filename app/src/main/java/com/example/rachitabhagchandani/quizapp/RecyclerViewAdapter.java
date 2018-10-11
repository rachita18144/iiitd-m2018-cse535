package com.example.rachitabhagchandani.quizapp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    ArrayList<Question> mDataset = new ArrayList<Question>();
    Context mContext;

    public RecyclerViewAdapter(ArrayList<Question> dataSet, Context mcontext) {
        this.mDataset = dataSet;
        this.mContext = mcontext;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtViewTitle;
        public CardView cardView;
        public IMyViewHolderClicks mListener;

        public ViewHolder(View itemLayoutView, IMyViewHolderClicks listener) {
            super(itemLayoutView);
            mListener = listener;
            cardView = (CardView) itemLayoutView.findViewById(R.id.card_view);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.question);
            txtViewTitle.setOnClickListener(this);
            itemLayoutView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
                mListener.itemClicked(v, getAdapterPosition());
        }

        public static interface IMyViewHolderClicks {
            public void itemClicked(View view, int position);
        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_view, parent, false);

        RecyclerViewAdapter.ViewHolder vh = new ViewHolder(v, new RecyclerViewAdapter.ViewHolder.IMyViewHolderClicks() {
            public void itemClicked(View v, int pos) {
                MainActivity mainActivity = (MainActivity) mContext;

                // this will open replace current frgament to quiz fragment
                mainActivity.openQuizFragment(mDataset.get(pos).question, mDataset.get(pos).id);
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtViewTitle.setText(mDataset.get(position).question);
        holder.cardView.setTag(R.string.list_index, position);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
