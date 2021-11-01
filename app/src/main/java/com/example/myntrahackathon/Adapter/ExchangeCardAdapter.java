package com.example.myntrahackathon.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myntrahackathon.Fragments.ExchangeFragment;
import com.example.myntrahackathon.Fragments.PlayQuizFragment;
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
        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyItemRemoved(position);

                Fragment someFragment = new ExchangeFragment();
                FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragmentContainer, someFragment );
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView ivCardLogo;
        TextView tvCardName, tvCardStatus;
        View mview;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ivCardLogo = itemView.findViewById(R.id.ivCardLogo);
            tvCardName = itemView.findViewById(R.id.tvCardName);
            tvCardStatus = itemView.findViewById(R.id.tvCardStatus);
            mview = itemView;
        }
    }
}
