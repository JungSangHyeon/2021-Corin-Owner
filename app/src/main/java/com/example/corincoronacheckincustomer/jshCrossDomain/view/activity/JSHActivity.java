package com.example.corincoronacheckincustomer.jshCrossDomain.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public abstract class JSHActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(this.getLayoutId());
        this.associate();
        this.initialize();
    }
    protected abstract int getLayoutId();
    protected abstract void associate();
    protected abstract void initialize();
}
