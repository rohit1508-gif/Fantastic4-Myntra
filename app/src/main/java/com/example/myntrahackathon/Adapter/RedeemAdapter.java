package com.example.myntrahackathon.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myntrahackathon.Modal.Redeem;
import com.example.myntrahackathon.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class RedeemAdapter extends RecyclerView.Adapter<RedeemAdapter.ViewHolder>{
    List<Redeem> list;
    Context ctx;
    public RedeemAdapter(List<Redeem> list,Context ctx){
        this.list=list;
        this.ctx=ctx;
    }
    @NonNull
    @Override
    public RedeemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_redeem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RedeemAdapter.ViewHolder holder, int position) {
        Redeem l = list.get(position);
        Picasso.get().load(l.getImage()).into(holder.img);
        holder.t.setData(l.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        Text t;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            t = itemView.findViewById(R.id.text);
        }
    }
}
