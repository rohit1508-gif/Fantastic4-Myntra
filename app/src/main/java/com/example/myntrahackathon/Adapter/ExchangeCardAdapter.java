package com.example.myntrahackathon.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myntrahackathon.ModalClasses.ExchangeCard;
import com.example.myntrahackathon.R;
import com.squareup.picasso.Picasso;

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
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exchange_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        ExchangeCard card = list.get(position);
        Picasso.get().load(card.getImage()).into(holder.ivCardLogo);
        holder.tvCardName.setText(card.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView ivCardLogo;
        TextView tvCardName, tvCardStatus;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ivCardLogo = itemView.findViewById(R.id.ivCardLogo);
            tvCardName = itemView.findViewById(R.id.tvCardName);
            tvCardStatus = itemView.findViewById(R.id.tvCardStatus);
        }
    }
}
