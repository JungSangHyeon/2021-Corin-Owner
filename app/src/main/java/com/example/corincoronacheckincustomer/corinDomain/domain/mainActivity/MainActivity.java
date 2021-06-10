package com.example.corincoronacheckincustomer.corinDomain.domain.mainActivity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.corincoronacheckincustomer.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.corincoronacheckincustomer.corinDomain.crossDomain.Constant.MainActivity_NavigationMap;

public class MainActivity extends AppCompatActivity {

    //  자산화 하고... 아이콘... 툴바 프래그 먼트
    // CoronaInfoFragment Loading Animation

    // Associate
        // ETC
        private NavController mainNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                CoronaInfoCrawler.getCoronaInfo();
//            }
//        }.start();

        // Associate View
        BottomNavigationView bottomNavigationView = this.findViewById(R.id.mainActivity_bottomNavigationView);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.mainActivity_fragmentContainer);

        // Associate ETC
        this.mainNavController = navHostFragment.getNavController();

        // Initialize View
        bottomNavigationView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
    }

    private boolean onNavigationItemSelected(MenuItem menuItem) {
        this.mainNavController.navigate(MainActivity_NavigationMap.get(menuItem.getItemId()));
        return true;
    }
}