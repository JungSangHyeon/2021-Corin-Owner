package com.example.corincoronacheckinowner.jshCrossDomain.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public abstract class JSHActivity extends AppCompatActivity {

    // Component
    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(this.getLayoutId());
        this.associate();
        this.initialize();

        // Create Component
        this.launcher = this.registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> this.onActivityResult(result)
        );
    }
    protected abstract int getLayoutId();
    protected abstract void associate();
    protected abstract void initialize();

    public ActivityResultLauncher<Intent> getActivityResultLauncher(){ return this.launcher; }
    protected void onActivityResult(ActivityResult result) { }
}
