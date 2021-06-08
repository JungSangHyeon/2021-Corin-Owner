package com.example.corincoronacheckincustomer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    //  UI Style로 뽑고 Package 정리하고, 자산화 하고... 아이콘... 툴바 프래그 먼트

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            new Thread(){
        @Override
        public void run() {
            super.run();
            Log.d("TESTTEST", System.currentTimeMillis()+"");
            Document doc = null;
            try {
                doc = Jsoup.connect("https://www.google.com/search?q=%EC%BD%94%EB%A1%9C%EB%82%98+%ED%98%84%ED%99%A9&oq=zhfhsk+gus&aqs=chrome.1.69i57j69i59i131i433j0l8.1927j0j7&sourceid=chrome&ie=UTF-8").get();
//                    doc = Jsoup.connect("https://movie.naver.com/movie/running/current.nhn").get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements mElementDatas = doc.select("div[jsname=fUyIqc]"); //1
//                Elements mElementDatas = doc.select("ul[class=lst_detail_t1]").select("li"); //1

            Log.d("TESTTEST", "START");

            for(Element elem : mElementDatas){
                Log.d("TESTTEST", elem.select("span").text());
            }

            Log.d("TESTTEST", "END");
            Log.d("TESTTEST", System.currentTimeMillis()+"");
        }
    }.start();



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