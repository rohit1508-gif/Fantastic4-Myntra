package com.example.myntrahackathon.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.airbnb.lottie.LottieAnimationView;
import com.example.myntrahackathon.MainActivity;
import com.example.myntrahackathon.R;

public class ExchangeFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private Button btnSchedule, btnContinue;
    private String cloth;
    private Spinner spinner;
    private LottieAnimationView lottie_exchange;
    private TextView tvScheduled, tvThankYou;
    private EditText etAddress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exchange, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Myntra Play");
        MainActivity.goToFragment = "RedeemFragment";

        spinner = view.findViewById(R.id.clothes_spinner);
        btnContinue = view.findViewById(R.id.btn_continue);
        btnSchedule = view.findViewById(R.id.btn_schedule);
        lottie_exchange = view.findViewById(R.id.lottie_exchange);
        tvScheduled = view.findViewById(R.id.tvScheduled);
        etAddress = view.findViewById(R.id.etAddress);
        tvThankYou = view.findViewById(R.id.tvThankYou);
        adaptUiPriorSchedule();
    }

    private void adaptUiPriorSchedule() {
        setSpinner();
        lottie_exchange.setVisibility(View.GONE);
        tvScheduled.setVisibility(View.GONE);
        tvThankYou.setVisibility(View.GONE);
        btnContinue.setVisibility(View.GONE);
        btnSchedule.setOnClickListener(v -> {
            if (etAddress.getText().toString().trim().isEmpty()) {
                Toast.makeText(getContext(), "Please enter the pickup address", Toast.LENGTH_SHORT).show();
            } else {
                ProgressDialog progressDialog = ProgressDialog.show(getContext(), null, "Scheduling your pickup...");
                new Handler().postDelayed(() -> {
                    progressDialog.dismiss();
                    adaptUiAfterSchedule();
                }, 2000);
            }
        });
    }

    private void adaptUiAfterSchedule() {
        MainActivity.goToFragment = "NoAction";
        etAddress.setVisibility(View.GONE);
        btnSchedule.setVisibility(View.GONE);
        spinner.setVisibility(View.GONE);
        lottie_exchange.setVisibility(View.VISIBLE);
        lottie_exchange.playAnimation();

        new Handler().postDelayed(() -> {
            btnContinue.setOnClickListener(v -> {
                if (getFragmentManager() != null) {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainer, new HomeFragment());
                    transaction.commit();
                }
            });
            tvScheduled.setText("Your pickup request for a " + cloth + " has been scheduled. Within 2 working days, our delivery partner executive will reach you for quality check and pickup. You'll receive your discount voucher once this process is completed.");
            tvScheduled.setVisibility(View.VISIBLE);
            tvThankYou.setVisibility(View.VISIBLE);
            btnContinue.setVisibility(View.VISIBLE);
        }, 1100);
    }

    private void setSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.clothes_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            btnSchedule.setClickable(false);
            Toast.makeText(getContext(), "Please choose an item", Toast.LENGTH_SHORT).show();
        } else {
            cloth = (String) parent.getItemAtPosition(position);
            btnSchedule.setClickable(true);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
