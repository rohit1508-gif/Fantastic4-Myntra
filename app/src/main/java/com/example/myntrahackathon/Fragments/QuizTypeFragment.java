package com.example.myntrahackathon.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myntrahackathon.R;

public class QuizTypeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_quiztype, container, false);
        view.findViewById(R.id.type1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openquiz("");
            }
        });

        view.findViewById(R.id.type2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openquiz("");
            }
        });

        view.findViewById(R.id.type3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openquiz("");
            }
        });
        return view;
    }

    public void openquiz(String s){
        Fragment someFragment = new QuizFragment();
        assert getFragmentManager() != null;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container1, someFragment );
        transaction.addToBackStack(null);
        Bundle bundle = new Bundle();
        bundle.putString("type",s);
        someFragment.setArguments(bundle);
        transaction.commit();
    }
}
