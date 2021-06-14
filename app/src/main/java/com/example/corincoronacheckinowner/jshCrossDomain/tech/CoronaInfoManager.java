package com.example.corincoronacheckinowner.jshCrossDomain.tech;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class CoronaInfoManager {

    // Static Value
    private static String GoogleCoronaInfoUrl = "https://www.google.com/search?q=%EC%BD%94%EB%A1%9C%EB%82%98+%ED%98%84%ED%99%A9&oq=zhfhsk+gus&aqs=chrome.1.69i57j69i59i131i433j0l8.1927j0j7&sourceid=chrome&ie=UTF-8";
    private static String GoogleMoreCoronaInfoUrl = "https://news.google.com/covid19/map?hl=ko&mid=%2Fm%2F06qd3&gl=KR&ceid=KR%3Ako";
    private static String TargetJsname = "fUyIqc";
    public enum CoronaInfoIndex {
        KoreaTotalPatient,
        KoreaCured,
        KoreaDeath,
        WorldTotalPatient,
        WorldTotalDeath
    }

    // Working Variable
    private static ArrayList<String> LastCoronaInfo;

    /**
     * Don't Call This In Main Thread
     * Takes 1.5~2sec
     * @return Corona Info From Google
     * @see CoronaInfoIndex
     */
    public static ArrayList<String> getCoronaInfo() {
        ArrayList<String> info = new ArrayList<>();
        try {
            Document document = Jsoup.connect(GoogleCoronaInfoUrl).get();
            Elements elements = document.select("div[jsname="+TargetJsname+"]");
            for (Element element : elements) {
                String s = element.select("span").text().split(" ")[0];
                if(s.charAt(0)!='+') info.add(s);
            }
        } catch (IOException e) { e.printStackTrace(); }
        LastCoronaInfo = info;
        Log.d("JSH "+ CoronaInfoManager.class.getSimpleName(), info.toString());
        return info;
    }

    public static ArrayList<String> getLastCoronaInfo(){
        if(LastCoronaInfo==null) Log.d("JSH "+ CoronaInfoManager.class.getSimpleName(), "LastCoronaInfo Is Null");
        return LastCoronaInfo;
    }

    /**
     * @return Intent To Open GoogleMoreCoronaInfoUrl
     */
    public static Intent getCoronaMoreInfoIntent(){
        return new Intent(Intent.ACTION_VIEW, Uri.parse(GoogleMoreCoronaInfoUrl));
    }
}
