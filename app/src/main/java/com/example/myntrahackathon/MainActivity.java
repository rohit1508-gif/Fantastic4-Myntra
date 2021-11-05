package com.example.myntrahackathon;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.myntrahackathon.Fragments.HomeFragment;
import com.example.myntrahackathon.Fragments.LoginFragment;
import com.example.myntrahackathon.Fragments.RedeemFragment;

public class MainActivity extends AppCompatActivity {

    public static String fragment="";
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
        if(fragment.equals("Home")){
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }
        else if(fragment.equals("ActiveQuiz")){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                    new HomeFragment()).commit();
        }
        else if(fragment.equals("Exchange")){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                    new RedeemFragment()).commit();
        }
        else if(fragment.equals("Leaderboard")){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                    new HomeFragment()).commit();
        }
        else if(fragment.equals("Redeem")){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                    new HomeFragment()).commit();
        }
        else if(fragment.equals("NoWork")){

        }
    }
}