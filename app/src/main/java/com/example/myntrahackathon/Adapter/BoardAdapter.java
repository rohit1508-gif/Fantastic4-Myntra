package com.example.myntrahackathon.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myntrahackathon.ModalClasses.UserScore;
import com.example.myntrahackathon.R;

import org.w3c.dom.Text;

import java.util.List;

public class BoardAdapter  extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {
    List<UserScore> list;
    Context ctx;
    public BoardAdapter(List<UserScore> list, Context ctx) {
        this.list=list;
        this.ctx=ctx;
    }

    @NonNull
    @Override
    public BoardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_redeem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardAdapter.ViewHolder holder, int position) {
        UserScore l = list.get(position);
        holder.t.setData(position+"  " + l.getName());
        String score = String.valueOf(l.getScore());
        holder.t1.setData(score);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        Text t,t1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t = itemView.findViewById(R.id.name);
            t1 = itemView.findViewById(R.id.score);
        }
    }
}
