package com.example.corincoronacheckinowner.corinDomain.view.mainActivity.coronaInfoFragment;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.corincoronacheckinowner.R;
import com.example.corincoronacheckinowner.jshCrossDomain.tech.CoronaInfoManager;
import com.example.corincoronacheckinowner.jshCrossDomain.view.fragment.JSHFragment;

import java.util.ArrayList;

public class CoronaInfoFragment extends JSHFragment {

    // Associate
        // View
        private TextView koreaTitleTextView, koreaTotalPatientTextView, koreaCuredTextView, koreaDeathTextView;
        private TextView worldTitleTextView, worldTotalPatientTextView, worldCuredTextView, worldDeathTextView;
        private Button moreInfoButton;
        private View loadingView;
        private ProgressBar progressBar;
    
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

        this.loadingView = view.findViewById(R.id.coronaInfoFragment_loadingView);
        this.progressBar = view.findViewById(R.id.coronaInfoFragment_progressBar);
    }

    @Override
    protected void initialize() {
        this.loadingView.setVisibility(View.GONE);
        this.progressBar.setVisibility(View.GONE);
        ArrayList<String> coronaInfo = CoronaInfoManager.getLastCoronaInfo();
        if (coronaInfo == null) {
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    getActivity().runOnUiThread(() -> {
                        loadingView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.VISIBLE);
                    });
                    ArrayList<String> newCoronaInfo = CoronaInfoManager.getCoronaInfo();
                    getActivity().runOnUiThread(() ->  {
                        updateView(newCoronaInfo);
                        loadingView.setVisibility(View.GONE);
                        progressBar.setVisibility(View.GONE);
                    });
                }
            }.start();
        } else {
            this.updateView(coronaInfo);
        }
    }
    public void updateView(ArrayList<String> coronaInfo){
        try {
            this.koreaTitleTextView.setText(R.string.coronaInfoFragment_koreaTitleText);
            this.koreaTotalPatientTextView.setText(coronaInfo.get(CoronaInfoManager.CoronaInfoIndex.KoreaTotalPatient.ordinal()));
            this.koreaCuredTextView.setText(coronaInfo.get(CoronaInfoManager.CoronaInfoIndex.KoreaCured.ordinal()));
            this.koreaDeathTextView.setText(coronaInfo.get(CoronaInfoManager.CoronaInfoIndex.KoreaDeath.ordinal()));
            this.worldTitleTextView.setText(R.string.coronaInfoFragment_worldTitleText);
            this.worldTotalPatientTextView.setText(coronaInfo.get(CoronaInfoManager.CoronaInfoIndex.WorldTotalPatient.ordinal()));
            this.worldDeathTextView.setText(coronaInfo.get(CoronaInfoManager.CoronaInfoIndex.WorldTotalDeath.ordinal()));
            this.moreInfoButton.setOnClickListener(v->startActivity(CoronaInfoManager.getCoronaMoreInfoIntent()));
        } catch (Exception e) {
            Toast.makeText(getContext(), R.string.coronaInfoFragment_siteChanged, Toast.LENGTH_SHORT).show();
        }
    }
}