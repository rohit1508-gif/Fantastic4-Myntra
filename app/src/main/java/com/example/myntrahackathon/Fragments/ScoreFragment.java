package com.example.myntrahackathon.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myntrahackathon.MainActivity;
import com.example.myntrahackathon.ModalClasses.Question;
import com.example.myntrahackathon.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ScoreFragment extends Fragment {
    private String quizId, quizName;
    private int score;
    private TextView tvUploading, tvCompleted, tvQuizName, tvScore;
    private LottieAnimationView lottie_submitted, lottie_uploading;
    private ImageView ivHome, ivLeaderBoard;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_score, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity.fragment="NoWork";
        if (getArguments() != null) {
        initializeViews(view);
        updateUIForUploading();
        getArgumentsFromBundle();
        sendScore();
    }

    private void initializeViews(View view) {
        tvUploading = view.findViewById(R.id.tvUploading);
        tvCompleted = view.findViewById(R.id.tvCompleted);
        tvQuizName = view.findViewById(R.id.tvQuizName);
        tvScore = view.findViewById(R.id.tvScore);
        lottie_submitted = view.findViewById(R.id.lottie_submitted);
        lottie_uploading = view.findViewById(R.id.lottie_uploading);
        ivHome = view.findViewById(R.id.ivHome);
        ivLeaderBoard = view.findViewById(R.id.ivLeaderBoard);

        ivHome.setOnClickListener(v -> {
            if (getFragmentManager() != null) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.fragmentContainer, new HomeFragment());
                transaction.commit();
            }
        });

        ivLeaderBoard.setOnClickListener(v -> {
            if (getFragmentManager() != null) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, new LeaderboardFragment());
                transaction.commit();
            }
        });
    }

    private void getArgumentsFromBundle() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            score = getArguments().getInt("Score");
            quizId = getArguments().getString("QuizId");
            quizName = getArguments().getString("QuizName");
        }
    }

    private void sendScore() {
        String url = "https://fantastic4-myntra.herokuapp.com/quiz/game/" + quizId + "/return";
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    updateUIForSubmitted();
                },
                error -> {

                });
        requestQueue.add(request);
    }

    private void updateUIForUploading() {
        lottie_submitted.setVisibility(View.GONE);
        tvCompleted.setVisibility(View.GONE);
        tvQuizName.setVisibility(View.GONE);
        tvScore.setVisibility(View.GONE);
        ivHome.setVisibility(View.GONE);
        ivLeaderBoard.setVisibility(View.GONE);

        lottie_uploading.setVisibility(View.VISIBLE);
        tvUploading.setVisibility(View.VISIBLE);
        lottie_uploading.playAnimation();
    }

    private void updateUIForSubmitted() {
        lottie_uploading.pauseAnimation();
        tvUploading.setVisibility(View.GONE);
        lottie_uploading.setVisibility(View.GONE);

        tvQuizName.setText(quizName);
        tvScore.setText(String.format("%d/5 answered correctly", score));

        tvCompleted.setVisibility(View.VISIBLE);
        tvQuizName.setVisibility(View.VISIBLE);
        tvScore.setVisibility(View.VISIBLE);
        ivHome.setVisibility(View.VISIBLE);
        ivLeaderBoard.setVisibility(View.VISIBLE);
        lottie_submitted.setVisibility(View.VISIBLE);
        lottie_submitted.playAnimation();
    }
}
