package com.example.myntrahackathon.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myntrahackathon.Adapter.SelectQuizAdapter;
import com.example.myntrahackathon.MainActivity;
import com.example.myntrahackathon.ModalClasses.QuizInfo;
import com.example.myntrahackathon.R;

import java.util.ArrayList;
import java.util.List;

public class ActiveQuizzesFragment extends Fragment {

    List<QuizInfo> quizzes;
    RecyclerView recyclerView;
    SelectQuizAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_active_quizzes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().setTitle("Active Quizzes");
        MainActivity.goToFragment = "HomeFragment";
        quizzes = new ArrayList<>();

        recyclerView = view.findViewById(R.id.rvSelectQuiz);
        recyclerView.setHasFixedSize(true);

        getData();
    }

    private void getData() {
        quizzes.add(new QuizInfo("Quiz 1", "Q1", ""));
        quizzes.add(new QuizInfo("Quiz 2", "Q2", ""));
        quizzes.add(new QuizInfo("Quiz 3", "Q3", ""));

        setAdapter();
    }

    private void setAdapter() {
        adapter = new SelectQuizAdapter(quizzes, this::startQuiz);
        recyclerView.setAdapter(adapter);
    }

    public void startQuiz(String quizId, String quizName) {
        Fragment playQuizFragment = new PlayQuizFragment();
        Bundle bundle = new Bundle();
        bundle.putString("quizId", quizId);
        bundle.putString("quizName", quizName);
        playQuizFragment.setArguments(bundle);
        if (getFragmentManager() != null) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, playQuizFragment);
            transaction.commit();
        }
    }
}
