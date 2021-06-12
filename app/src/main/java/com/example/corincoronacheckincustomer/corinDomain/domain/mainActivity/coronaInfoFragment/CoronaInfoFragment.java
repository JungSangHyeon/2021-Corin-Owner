package com.example.corincoronacheckincustomer.corinDomain.domain.mainActivity.coronaInfoFragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.corincoronacheckincustomer.R;
import com.example.corincoronacheckincustomer.crossDomain.CoronaInfoManager;
import com.example.corincoronacheckincustomer.jshCrossDomain.view.fragment.JSHFragment;

import java.util.ArrayList;

public class CoronaInfoFragment extends JSHFragment {

    // Associate
        // View
        private TextView koreaTitleTextView, koreaTotalPatientTextView, koreaCuredTextView, koreaDeathTextView;
        private TextView worldTitleTextView, worldTotalPatientTextView, worldCuredTextView, worldDeathTextView;
        private Button moreInfoButton;
    
    @Override protected int getLayoutId() { return R.layout.fragment_corona_info; }

    @Override
    protected void associate(View view) {
        View koreaInclude = view.findViewById(R.id.coronaInfoFragment_include_koreaCoronaInfo);
        this.koreaTitleTextView = koreaInclude.findViewById(R.id.coronaInfoLayout_locationTextView);
        this.koreaTotalPatientTextView = koreaInclude.findViewById(R.id.coronaInfoLayout_totalPatientValueTextView);
        this.koreaCuredTextView = koreaInclude.findViewById(R.id.coronaInfoLayout_totalCuredValueTextView);
        this.koreaDeathTextView = koreaInclude.findViewById(R.id.coronaInfoLayout_totalDeathValueTextView);

        View worldInclude = view.findViewById(R.id.coronaInfoFragment_include_worldCoronaInfo);
        this.worldTitleTextView = worldInclude.findViewById(R.id.coronaInfoLayout_locationTextView);
        this.worldTotalPatientTextView = worldInclude.findViewById(R.id.coronaInfoLayout_totalPatientValueTextView);
        this.worldCuredTextView = worldInclude.findViewById(R.id.coronaInfoLayout_totalCuredValueTextView);
        this.worldDeathTextView = worldInclude.findViewById(R.id.coronaInfoLayout_totalDeathValueTextView);

        this.moreInfoButton = view.findViewById(R.id.coronaInfoFragment_moreInfoButton);
    }

    @Override
    protected void initialize() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                ArrayList<String> coronaInfo = CoronaInfoManager.getCoronaInfo();
                getActivity().runOnUiThread(() -> {
                    try{
                        koreaTitleTextView.setText(R.string.coronaInfoFragment_koreaTitleText);
                        koreaTotalPatientTextView.setText(coronaInfo.get(CoronaInfoManager.CoronaInfoIndex.KoreaTotalPatient.ordinal()));
                        koreaCuredTextView.setText(coronaInfo.get(CoronaInfoManager.CoronaInfoIndex.KoreaCured.ordinal()));
                        koreaDeathTextView.setText(coronaInfo.get(CoronaInfoManager.CoronaInfoIndex.KoreaDeath.ordinal()));

                        worldTitleTextView.setText(R.string.coronaInfoFragment_worldTitleText);
                        worldTotalPatientTextView.setText(coronaInfo.get(CoronaInfoManager.CoronaInfoIndex.WorldTotalPatient.ordinal()));
                        worldDeathTextView.setText(coronaInfo.get(CoronaInfoManager.CoronaInfoIndex.WorldTotalDeath.ordinal()));

                        moreInfoButton.setOnClickListener(v->startActivity(CoronaInfoManager.getCoronaMoreInfoIntent()));
                    }catch (Exception e){
                        Toast.makeText(getContext(), R.string.coronaInfoFragment_siteChanged, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }.start();
    }
}