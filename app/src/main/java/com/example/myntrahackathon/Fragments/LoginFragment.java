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
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myntrahackathon.MainActivity;
import com.example.myntrahackathon.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginFragment extends Fragment {
    String username="";
    int score;
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
                    Fragment HomeFragment = new HomeFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("username", username);
                    bundle.putInt("score", score);
                    HomeFragment.setArguments(bundle);
                    if (getFragmentManager() != null) {
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragmentContainer, HomeFragment);
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
        String url = "https://fantastic4-myntra.herokuapp.com/login";
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JSONObject obj = new JSONObject();
        try {
            obj.put("username", emailId);
            obj.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    username = response.getString("username");
                    score = response.getInt("score");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
        return true;
    }
}