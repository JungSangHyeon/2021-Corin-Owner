package com.example.corincoronacheckincustomer.corinDomain.domain.mainActivity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.corincoronacheckincustomer.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.corincoronacheckincustomer.corinDomain.crossDomain.Constant.MainActivity_NavigationMap;

public class MainActivity extends AppCompatActivity {

    //  Package 정리하고, 자산화 하고... 아이콘... 툴바 프래그 먼트

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
        this.mainNavController = Navigation.findNavController(this, R.id.mainActivity_fragmentContainer);

        // Initialize View
        bottomNavigationView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
    }

    private boolean onNavigationItemSelected(MenuItem menuItem) {
        this.mainNavController.navigate(MainActivity_NavigationMap.get(menuItem.getItemId()));
        return true;
    }
}