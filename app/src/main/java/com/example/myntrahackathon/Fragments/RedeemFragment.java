package com.example.myntrahackathon.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myntrahackathon.Adapter.ExchangeCardAdapter;
import com.example.myntrahackathon.MainActivity;
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
        getActivity().setTitle("Myntra Play");
        MainActivity.goToFragment = "HomeFragment";
        cards = new ArrayList<>();
        cards.add(new ExchangeCard("Exchange Card -\n   upto 80% off on denims", "Redeem before 24 Dec 2021"));
        cards.add(new ExchangeCard("Exchange Card -\n   upto 40% off on anything", "Redeem before 31 Dec 2021"));
        cards.add(new ExchangeCard("Exchange Card -\n   upto 65% off on sarees", "Redeem before 12 Jan 2022"));

        adapter = new ExchangeCardAdapter(cards, getContext());
        recyclerView = view.findViewById(R.id.rvExchangeCard);
        recyclerView.setHasFixedSize(true);

        ProgressDialog progressDialog = ProgressDialog.show(getContext(), null, "Fetching your exchange cards...");
        new Handler().postDelayed(() -> {
            progressDialog.dismiss();
            recyclerView.setAdapter(adapter);
        }, 2000);
    }
}
