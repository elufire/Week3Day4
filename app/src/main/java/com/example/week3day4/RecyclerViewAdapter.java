package com.example.week3day4;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.week3day4.GithubRepository.Result;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    ArrayList<Result> resultsArrayList;
    String TAG;

    public RecyclerViewAdapter(ArrayList<Result> resultsArrayList) {
        this.resultsArrayList = resultsArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int position) {
        Result result = resultsArrayList.get(position);

        if (result != null) {
            String nodeId = result.getNodeId();
            String name = result.getName();
            String fullName = result.getFullName();
            viewHolder.setResult(result);
            viewHolder.tvNodeId.setText(nodeId);
            viewHolder.tvName.setText(name);
            viewHolder.tvFullName.setText(fullName);
        }
    }

    @Override
    public int getItemCount() {
        return resultsArrayList != null ? resultsArrayList.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNodeId;
        TextView tvName;
        TextView tvFullName;

        Result itemResult;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            tvNodeId = itemView.findViewById(R.id.tvNodeId);
            tvName = itemView.findViewById(R.id.tvName);
            tvFullName = itemView.findViewById(R.id.tvFullName);
            setResult(itemResult);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                   EventBus.getDefault().post(new ResultEvent(itemResult));
//                }
//            });
        }


        public void setResult(Result itemResult){ this.itemResult = itemResult;}
    }

    public void addResult(Result result){
        //System.out.println(name);
        resultsArrayList.add(result);
        notifyDataSetChanged();
    }
}