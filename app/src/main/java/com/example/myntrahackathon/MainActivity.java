package com.example.myntrahackathon;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.myntrahackathon.Fragments.HomeFragment;
import com.example.myntrahackathon.Fragments.LoginFragment;
import com.example.myntrahackathon.Fragments.RedeemFragment;

public class MainActivity extends AppCompatActivity {

    public static String goToFragment = "CloseApplication";
    private long backPressedTime = 0;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if (intent.getStringExtra("DESTINATION_FRAGMENT").equals("HOME_FRAGMENT")) {
            if (getFragmentManager() != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.fragmentContainer, new LoginFragment());
                transaction.commit();
            }
        }
    }

    @Override
    public void onBackPressed() {
        switch (goToFragment) {
            case "HomeFragment":
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.fragmentContainer,
                                new HomeFragment()).commit();
                break;
            case "RedeemFragment":
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                        new RedeemFragment()).commit();
                break;
            case "CloseApplication":
                if (backPressedTime + 2000 > System.currentTimeMillis()) {
                    backToast.cancel();
                    finishAffinity();
                    finishActivity(1);
                } else {
                    backToast = Toast.makeText(getBaseContext(), "Press again to exit", Toast.LENGTH_SHORT);
                    backToast.show();
                }
                backPressedTime = System.currentTimeMillis();
        }
    }
}