package com.example.myntrahackathon;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Objects.requireNonNull(getSupportActionBar()).hide();

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.putExtra("DESTINATION_FRAGMENT", "HOME_FRAGMENT");
            startActivity(intent);
            finish();
        }, SPLASH_SCREEN_TIME_OUT);
    }
}