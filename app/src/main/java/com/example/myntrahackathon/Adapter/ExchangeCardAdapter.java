package com.example.myntrahackathon.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myntrahackathon.ModalClasses.ExchangeCard;
import com.example.myntrahackathon.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class ExchangeCardAdapter extends RecyclerView.Adapter<ExchangeCardAdapter.MyHolder> {

    List<ExchangeCard> list;
    Context context;

    public ExchangeCardAdapter(List<ExchangeCard> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_redeem, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        ExchangeCard card = list.get(position);
        Picasso.get().load(card.getImage()).into(holder.img);
        holder.t.setData(card.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView img;
        Text t;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            t = itemView.findViewById(R.id.text);
        }
    }
}
