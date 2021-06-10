package com.example.corincoronacheckincustomer.jshCrossDomain.model.viewModel;

import android.app.Activity;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.corincoronacheckincustomer.jshCrossDomain.model.room.entity.JSHEntity;

import java.util.ArrayList;
import java.util.List;

public class JSHViewModelTool implements Observer {

    // Associate
        // View
        private final Activity activity;
        // Model
        protected JSHViewModel model;
        private final ViewModelToolCallback callback;
        private final ArrayList<JSHEntity> jshEntities;

    // Constructor
    public JSHViewModelTool(Activity activity, ViewModelStoreOwner viewModelStoreOwner, ViewModelToolCallback callback) {
        // Create Component
        this.jshEntities = new ArrayList<>();

        // Associate View
        this.activity=activity;

        // Associate Model
        this.model = new ViewModelProvider(viewModelStoreOwner,
                ViewModelProvider.AndroidViewModelFactory.getInstance(activity.getApplication())).get(JSHViewModel.class);
        this.callback=callback;
    }
    public void startObserve(){ this.model.getDataset().observe((LifecycleOwner) this.activity, this); }
    public void stopObserve(){ this.model.getDataset().removeObservers((LifecycleOwner) this.activity); }

    @Override
    public void onChanged(Object o) {
        this.updateJSHEntities();
        this.callback.viewModelDataUpdated();
    }
    private void updateJSHEntities(){
        this.jshEntities.clear();
        List<JSHEntity> jshEntities = this.model.getDataset().getValue();
        for(JSHEntity jshEntity : jshEntities) this.jshEntities.add(jshEntity);
    }

    public ArrayList<JSHEntity> getJSHEntities(){ return this.jshEntities; }
    public JSHViewModel getModel() { return model; }
}
