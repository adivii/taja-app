package com.utstam.taja;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.utstam.taja.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, FragmentChangeListener{
    ActivityMainBinding activityMainBinding;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(activityMainBinding.getRoot());

        // Deactivate Dark Mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // Remove Action Bar
        getSupportActionBar().hide();

        // Setup Bottom Navigation
        bottomNavigationView = activityMainBinding.mainNavigationView;
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.homePage);
    }

    // Implement changeFragment() method to enable the fragment changing feature from the current fragment
    @Override
    public void changeFragment(int container, Fragment fragment) {
        getSupportFragmentManager().
                beginTransaction().
                replace(container, fragment).
                addToBackStack(Integer.toString(fragment.getId())).
                commit();
    }

    // Create Fragment
    HomeFragment homeFragment = new HomeFragment();
    ContentFragment contentFragment = new ContentFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.homePage:
                changeFragment(activityMainBinding.container.getId(), homeFragment);
                return true;
            case R.id.contentPage:
                changeFragment(activityMainBinding.container.getId(), contentFragment);
                return true;
            case R.id.profilePage:
                changeFragment(activityMainBinding.container.getId(), profileFragment);
                return true;
            default:
                return false;
        }
    }


}