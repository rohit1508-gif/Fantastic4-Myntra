package com.example.myntrahackathon.Fragments;

import static com.example.myntrahackathon.MainActivity.userId;
import static com.example.myntrahackathon.MainActivity.userName;
import static com.example.myntrahackathon.MainActivity.userScore;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.airbnb.lottie.LottieAnimationView;
import com.example.myntrahackathon.MainActivity;
import com.example.myntrahackathon.ModalClasses.LoginUser;
import com.example.myntrahackathon.R;

import java.util.HashMap;
import java.util.Map;

public class LoginFragment extends Fragment {
    ProgressBar progressBar;
    LottieAnimationView lottieAnimationView;
    Map<String, LoginUser> userMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Myntra Play");
        MainActivity.goToFragment = "CloseApplication";
        getDatabase();
        EditText etEmail = view.findViewById(R.id.etEmail);
        EditText etPassword = view.findViewById(R.id.etPassword);
        progressBar = view.findViewById(R.id.progressBarLogin);
        lottieAnimationView = view.findViewById(R.id.lottie_login_successful);

        view.findViewById(R.id.btnLogIn).setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            authenticate(etEmail.getText().toString().trim(), etPassword.getText().toString().trim());
        });
    }

    private void getDatabase() {
        userMap = new HashMap<>();
        userMap.put("abcd", new LoginUser(1, "ABCD", 30, "abcd"));
        userMap.put("shubha", new LoginUser(2, "SHUBH123", 50, "shubha"));
        userMap.put("rohit", new LoginUser(3, "ROHIT123", 40, "rohit"));
        userMap.put("Shruti", new LoginUser(4, "SHRUTI123", 40, "Shruti"));
        userMap.put("Abhinav", new LoginUser(5, "ABHI123", 60, "Abhinav"));
        userMap.put("Rohan", new LoginUser(6, "ROHAN123", 20, "Rohan"));
        userMap.put("Payal", new LoginUser(7, "PAYAL123", 0, "Payal"));
    }

    private void authenticate(String username, String password) {
        new Handler().postDelayed(() -> {
            progressBar.setVisibility(View.GONE);
            if (userMap.containsKey(username) && userMap.get(username).getPassword().equals(password)) {
                lottieAnimationView.setVisibility(View.VISIBLE);
                lottieAnimationView.playAnimation();
                userId = userMap.get(username).getUser_id();
                userScore = userMap.get(username).getScore();
                userName = userMap.get(username).getUsername();
                new Handler().postDelayed(() -> {
                    if (getFragmentManager() != null) {
                        HomeFragment homeFragment = new HomeFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("username", username);
                        homeFragment.setArguments(bundle);
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragmentContainer, homeFragment);
                        transaction.commit();
                    }
                }, 1700);
            } else {
                Toast.makeText(getContext(), "Wrong username and/or password!", Toast.LENGTH_SHORT).show();
            }
        }, 2000);
    }
}