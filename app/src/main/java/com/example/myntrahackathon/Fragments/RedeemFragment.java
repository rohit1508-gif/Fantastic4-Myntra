package com.example.myntrahackathon.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myntrahackathon.Adapter.ExchangeCardAdapter;
import com.example.myntrahackathon.ModalClasses.ExchangeCard;
import com.example.myntrahackathon.R;

import java.util.ArrayList;
import java.util.List;

public class RedeemFragment extends Fragment {

    RecyclerView recyclerView;
    ExchangeCardAdapter adapter;
    List<ExchangeCard> cards;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_redeem, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cards = new ArrayList<>();
        cards.add(new ExchangeCard("", "Exchange Card", "Use before 24 December 2021"));
        cards.add(new ExchangeCard("", "Get 20% discount", "Expired on 4 August 2021"));
        cards.add(new ExchangeCard("", "", "Use before 24 December 2021"));

        adapter = new ExchangeCardAdapter(cards, getContext());
        recyclerView = view.findViewById(R.id.rvExchangeCard);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);
    }
}
