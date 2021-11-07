package com.example.myntrahackathon.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myntrahackathon.MainActivity;
import com.example.myntrahackathon.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginFragment extends Fragment {
    ProgressBar progressBar;
    LottieAnimationView lottieAnimationView;

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
        progressBar = view.findViewById(R.id.progressBarLogin);
        lottieAnimationView = view.findViewById(R.id.lottie_login_successful);

        view.findViewById(R.id.btnLogIn).setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            authenticate(etEmail.getText().toString().trim(), etPassword.getText().toString().trim());
        });
    }

    private void authenticate(String emailId, String password) {
        new Handler().postDelayed(() -> {
            progressBar.setVisibility(View.GONE);
            lottieAnimationView.setVisibility(View.VISIBLE);
            lottieAnimationView.playAnimation();
            new Handler().postDelayed(() -> {
                if (getFragmentManager() != null) {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainer, new HomeFragment());
                    transaction.commit();
                }
            }, 1700);
        }, 2000);
    }
}