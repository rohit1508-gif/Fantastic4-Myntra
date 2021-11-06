package com.example.myntrahackathon.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myntrahackathon.Fragments.ExchangeFragment;
import com.example.myntrahackathon.ModalClasses.ExchangeCard;
import com.example.myntrahackathon.R;

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
        holder.tvCardName.setText(card.getTitle());
        holder.tvCardStatus.setText(card.getStatus());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragmentContainer, new ExchangeFragment());
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView tvCardName, tvCardStatus;
        View view;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvCardName = itemView.findViewById(R.id.tvCardName);
            tvCardStatus = itemView.findViewById(R.id.tvCardStatus);
            view = itemView;
        }
    }
}
