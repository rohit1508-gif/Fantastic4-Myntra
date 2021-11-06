package com.example.myntrahackathon.Fragments;

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
import com.example.myntrahackathon.R;

public class LoginFragment extends Fragment {

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
        EditText etEmail = view.findViewById(R.id.etEmail);
        EditText etPassword = view.findViewById(R.id.etPassword);
        ProgressBar progressBar = view.findViewById(R.id.progressBarLogin);

        view.findViewById(R.id.btnLogIn).setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            if (authenticate(etEmail.getText().toString().trim(), etPassword.getText().toString().trim())) {
                progressBar.setVisibility(View.GONE);
                LottieAnimationView lottieAnimationView = view.findViewById(R.id.lottie_login_successful);
                lottieAnimationView.setVisibility(View.VISIBLE);
                lottieAnimationView.playAnimation();

                new Handler().postDelayed(() -> {
                    if (getFragmentManager() != null) {
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragmentContainer, new HomeFragment());
                        transaction.commit();
                    }
                }, 1700);

            } else {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Wrong Email ID or Password !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Boolean authenticate(String emailId, String password) {
        return true;
    }
}