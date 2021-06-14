package com.example.corincoronacheckinowner.corinDomain;

import com.example.corincoronacheckinowner.R;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class Constant {

    // Main Activity
    public static final Map<Integer, Integer> MainActivity_NavigationMap = new HashMap<Integer, Integer>() {{
        put(R.id.action_now_corona, R.id.action_global_coronaInfoFragment);
        put(R.id.action_scan, R.id.action_global_scanFragment);
        put(R.id.action_history, R.id.action_global_historyFragment);
    }};

    // History Fragment
    public static final SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");

    // History Entity
    public enum Gender {eMan, eWomen, eSecrete}

    // In And Out Item
    public static final int[] AgeGroupChipIds = {
            R.id.inAndOutInfoLayout_ageChip10,
            R.id.inAndOutInfoLayout_ageChip20,
            R.id.inAndOutInfoLayout_ageChip30,
            R.id.inAndOutInfoLayout_ageChip40,
            R.id.inAndOutInfoLayout_ageChip50,
            R.id.inAndOutInfoLayout_ageChip60
    };
}
