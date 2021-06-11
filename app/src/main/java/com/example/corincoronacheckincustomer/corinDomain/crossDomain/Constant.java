package com.example.corincoronacheckincustomer.corinDomain.crossDomain;

import com.example.corincoronacheckincustomer.R;

import java.util.HashMap;
import java.util.Map;

public class Constant {

    // Main Activity
    public static Map<Integer, Integer> MainActivity_NavigationMap = new HashMap<Integer, Integer>() {{
        put(R.id.action_now_corona, R.id.action_global_coronaInfoFragment);
        put(R.id.action_check_in, R.id.action_global_checkInFragment);
        put(R.id.action_my_corin, R.id.action_global_myCorinFragment);
    }};


    // User Entity
    public enum Gender {eMan, eWomen, eSecrete}
    public static Map<Integer, Gender> GenderMap = new HashMap<Integer, Gender>() {{
        put(R.id.settingPrivateInfoLayout_genderSeceretChip, Gender.eSecrete);
        put(R.id.settingPrivateInfoLayout_genderManChip, Gender.eMan);
        put(R.id.settingPrivateInfoLayout_genderWomenChip, Gender.eWomen);
    }};
}
