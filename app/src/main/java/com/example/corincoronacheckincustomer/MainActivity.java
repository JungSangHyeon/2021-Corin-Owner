package com.example.corincoronacheckincustomer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    //  UI Style로 뽑고 Package 정리하고, 자산화 하고... 아이콘... 툴바 프래그 먼트

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        BottomNavigationView bottomNavigationView = this.findViewById(R.id.mainActivity_bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            NavController navController = Navigation.findNavController(this, R.id.mainActivity_fragmentContainer);
            switch(item.getItemId()){
                case R.id.action_now_corona: navController.navigate(R.id.action_global_coronaInfoFragment); break;
                case R.id.action_check_in: navController.navigate(R.id.action_global_checkInFragment); break;
                case R.id.action_my_corin: navController.navigate(R.id.action_global_myCorinFragment); break;
            }
            return true;
        });
    }
}