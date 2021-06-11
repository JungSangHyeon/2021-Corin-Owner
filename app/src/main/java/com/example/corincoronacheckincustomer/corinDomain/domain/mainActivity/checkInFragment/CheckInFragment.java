package com.example.corincoronacheckincustomer.corinDomain.domain.mainActivity.checkInFragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;

import com.example.corincoronacheckincustomer.R;
import com.example.corincoronacheckincustomer.corinDomain.domain.loginActivity.LoginActivity;
import com.example.corincoronacheckincustomer.corinDomain.model.CorinEntity;
import com.example.corincoronacheckincustomer.jshCrossDomain.tech.ZXing;
import com.example.corincoronacheckincustomer.jshCrossDomain.view.fragment.JSHViewModelFragment;

public class CheckInFragment extends JSHViewModelFragment<CorinEntity> {

    // Associate
        // View
        private ImageView qrCodeImageView;
        private TextView descriptionTextView;
        private Button loginButton;

    @Override protected int getLayoutId() { return R.layout.fragment_check_in; }

    @Override
    protected void associate(View view) {
        // View
        this.qrCodeImageView = view.findViewById(R.id.checkInFragment_imageView);
        this.descriptionTextView = view.findViewById(R.id.checkInFragment_loginButtonDescriptionTextView);
        this.loginButton = view.findViewById(R.id.checkInFragment_loginButton);
    }
    @Override
    protected void initialize() {
        // View
        this.qrCodeImageView.setVisibility(View.INVISIBLE);
        this.descriptionTextView.setVisibility(View.INVISIBLE);
        this.loginButton.setVisibility(View.INVISIBLE);
        this.loginButton.setOnClickListener(v-> this.getActivityResultLauncher().launch(new Intent(this.getActivity(), LoginActivity.class)));
    }

    @Override protected Class getEntityClass() { return CorinEntity.class; }
    @Override protected void onModelUpdate() { this.updateView(); }

    @Override protected void onActivityResult(ActivityResult result) { this.updateView(); }

    private void updateView() {
        if(!this.entity.isLogin()){
            this.qrCodeImageView.setVisibility(View.VISIBLE);
            this.descriptionTextView.setVisibility(View.VISIBLE);
            this.loginButton.setVisibility(View.VISIBLE);
        }else{
            ZXing.showQRCode(this.qrCodeImageView, this.entity.getLoginUser().toString());
            this.qrCodeImageView.setVisibility(View.VISIBLE);
        }
    }
}