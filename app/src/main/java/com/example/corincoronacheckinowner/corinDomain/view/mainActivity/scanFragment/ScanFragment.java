package com.example.corincoronacheckinowner.corinDomain.view.mainActivity.scanFragment;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;

import com.example.corincoronacheckinowner.R;
import com.example.corincoronacheckinowner.corinDomain.Constant;
import com.example.corincoronacheckinowner.corinDomain.model.CorinEntity;
import com.example.corincoronacheckinowner.corinDomain.model.HistoryEntity;
import com.example.corincoronacheckinowner.jshCrossDomain.tech.ZXing;
import com.example.corincoronacheckinowner.jshCrossDomain.view.fragment.JSHViewModelFragment;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Calendar;

public class ScanFragment extends JSHViewModelFragment<CorinEntity> {

    // Associate
        // View
        private Button loginButton;

    @Override protected int getLayoutId() { return R.layout.fragment_scan; }

    @Override
    protected void associate(View view) {
        this.loginButton = view.findViewById(R.id.scanFragment_executeScannerButton);
    }
    @Override
    protected void initialize() {
        this.loginButton.setOnClickListener(v-> this.startScan());
    }

    @Override protected Class getEntityClass() { return CorinEntity.class; }
    @Override protected void onModelUpdate() { }

    @Override
    protected void onActivityResult(ActivityResult result) {
        if (result.getResultCode() == Activity.RESULT_OK) {
            IntentResult scanResult = IntentIntegrator.parseActivityResult(result.getResultCode(), result.getData());
            String data = scanResult.getContents(); // name age genderOrdinal
            String[] dataSet = data.split(" ");
            HistoryEntity historyEntity = new HistoryEntity(
                    Calendar.getInstance().getTimeInMillis(),
                    dataSet[0],
                    Integer.parseInt(dataSet[1]),
                    Constant.Gender.values()[Integer.parseInt(dataSet[2])]
            );
            this.entity.getHistoryEntities().add(historyEntity);
            this.save();
            Toast.makeText(this.getContext(), R.string.scanFragment_checkInCompleteText, Toast.LENGTH_LONG).show();
            this.startScan();
        }
    }

    private void startScan() {
        this.getActivityResultLauncher().launch(ZXing.getScanIntent(this.getActivity()));
    }
}