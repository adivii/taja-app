package com.utstam.taja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.utstam.taja.databinding.ActivityWelcomeBinding;

public class WelcomeActivity extends AppCompatActivity {
    ActivityWelcomeBinding activityWelcomeBinding;
    public String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityWelcomeBinding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(activityWelcomeBinding.getRoot());

        // Deactivate Dark Mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // Remove Action Bar
        getSupportActionBar().hide();

        // Set Default View
        changeFragment(activityWelcomeBinding.container.getId(), new LoginFragment());
    }

    public void changeFragment(int container, Fragment fragment) {
        getSupportFragmentManager().
                beginTransaction().
                replace(container, fragment).
                commit();
    }
}