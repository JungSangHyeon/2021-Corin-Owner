package com.example.corincoronacheckinowner.jshCrossDomain.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

public abstract class JSHFragment extends Fragment {

    // Component
    private ActivityResultLauncher<Intent> launcher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create Component
        this.launcher = this.registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> this.onActivityResult(result)
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(this.getLayoutId(), container, false);
    }
    protected abstract int getLayoutId();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.associate(view);
        this.initialize();
    }
    protected abstract void associate(View view);
    protected abstract void initialize();

    public ActivityResultLauncher<Intent> getActivityResultLauncher(){ return this.launcher; }
    protected void onActivityResult(ActivityResult result) { }
}
