package com.android.carrental.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.carrental.R;

import java.util.List;

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.ViewHolder> {
    private Context mContext;
    private List<String> mIssues;


    public IssueAdapter(Context mContext, List<String> mIssues) {
        this.mContext = mContext;
        this.mIssues = mIssues;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.help_item,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull IssueAdapter.ViewHolder viewHolder, int i) {
        viewHolder.issue.setText(mIssues.get(i));
    }

    @Override
    public int getItemCount() {
        return mIssues.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView issue;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            issue = (TextView) itemView.findViewById(R.id.help_issue);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}

