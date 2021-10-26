package com.example.myntrahackathon.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myntrahackathon.R;

public class QuizFragment extends Fragment {
    String type;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_quiz, container, false);
        Bundle bundle = this.getArguments();
        type=bundle.getString("type");

        return view;
    }
}
