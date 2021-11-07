package com.example.myntrahackathon.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myntrahackathon.MainActivity;
import com.example.myntrahackathon.R;

public class HomeFragment extends Fragment {
    String username="";
    int score;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Myntra Play");
        MainActivity.goToFragment = "CloseApplication";

        view.findViewById(R.id.button_quiz).setOnClickListener(v -> {
            if (getFragmentManager() != null) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, new ActiveQuizzesFragment());
                transaction.commit();
            }
        });

        view.findViewById(R.id.button_redeem).setOnClickListener(v -> {
            if (getFragmentManager() != null) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, new RedeemFragment());
                transaction.commit();
            }
        });

        view.findViewById(R.id.button_leaderboard).setOnClickListener(v -> {
            if (getFragmentManager() != null) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, new LeaderboardFragment());
                transaction.commit();
            }
        });
    }
}
