package com.example.corincoronacheckincustomer.corinDomain.view.mainActivity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.corincoronacheckincustomer.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.corincoronacheckincustomer.corinDomain.Constant.MainActivity_NavigationMap;

public class MainActivity extends AppCompatActivity {

    // Associate
        // ETC
        private NavController mainNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Associate View
        BottomNavigationView bottomNavigationView = this.findViewById(R.id.mainActivity_bottomNavigationView);

        // Associate ETC
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.mainActivity_fragmentContainer);
        this.mainNavController = navHostFragment.getNavController();

        // Initialize View
        bottomNavigationView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
    }

    private boolean onNavigationItemSelected(MenuItem menuItem) {
        this.mainNavController.navigate(MainActivity_NavigationMap.get(menuItem.getItemId()));
        return true;
    }

    @Override public void onBackPressed() { /* Do Nothing */}
}