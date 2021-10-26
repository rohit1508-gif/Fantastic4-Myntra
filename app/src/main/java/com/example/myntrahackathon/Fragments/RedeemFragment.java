package com.example.myntrahackathon.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myntrahackathon.Adapter.RedeemAdapter;
import com.example.myntrahackathon.ModalClasses.Redeem;
import com.example.myntrahackathon.R;

import java.util.ArrayList;
import java.util.List;

public class RedeemFragment extends Fragment {
    RecyclerView recyclerView;
    RedeemAdapter adapter;
    Context ctx;
    List<Redeem> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_redeem, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = new ArrayList<>();
        list.add(new Redeem("", "Exchange Card"));
        list.add(new Redeem("", "Get 20% discount"));
        list.add(new Redeem("", ""));
        recyclerView = view.findViewById(R.id.recycler_view);
        ctx = getActivity();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new RedeemAdapter(list, ctx);
        recyclerView.setAdapter(adapter);
    }
}
