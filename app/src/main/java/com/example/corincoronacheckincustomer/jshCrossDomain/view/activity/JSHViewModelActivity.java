package com.example.corincoronacheckincustomer.jshCrossDomain.view.activity;

import android.os.Bundle;

import com.example.corincoronacheckincustomer.jshCrossDomain.model.room.converter.JSHConverter;
import com.example.corincoronacheckincustomer.jshCrossDomain.model.room.entity.JSHEntity;
import com.example.corincoronacheckincustomer.jshCrossDomain.model.viewModel.JSHViewModelTool;
import com.example.corincoronacheckincustomer.jshCrossDomain.model.viewModel.ViewModelToolCallback;

import java.util.ArrayList;

/**
 * TODO 나중에 ViewModelTool 로 일부 코드 이동할 것
 */
public abstract class JSHViewModelActivity<T> extends JSHActivity implements ViewModelToolCallback {

    // Associate
        // Model
        protected T entity;

    // Component
    protected JSHViewModelTool viewModelTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(this.getLayoutId());

        this.associate();
        this.initialize();

        // Create Component
        this.viewModelTool = new JSHViewModelTool(this, this, this);
    }
    protected abstract int getLayoutId();

    @Override
    protected void onResume() {
        super.onResume();
        this.viewModelTool.startObserve();
    }
    @Override
    protected void onPause() {
        super.onPause();
        this.viewModelTool.stopObserve();
        this.save();
    }
    public void save(){
        ArrayList<JSHEntity> jshEntityArray = this.viewModelTool.getJSHEntities();
        if(jshEntityArray.size()!=0){
            JSHEntity jshEntity = jshEntityArray.get(0);
            jshEntity.setEntityString(JSHConverter.fromTypeToString(this.entity));
            this.viewModelTool.getModel().update(jshEntity);
        }
    }

    @Override
    public void viewModelDataUpdated() {
        ArrayList<JSHEntity> jshEntities = this.viewModelTool.getJSHEntities();
        if(jshEntities.size()==0){
            JSHEntity jshEntity = new JSHEntity();
            try { jshEntity.setEntityString(JSHConverter.fromTypeToString(this.getEntityClass().newInstance())); }
            catch (IllegalAccessException | InstantiationException e) { e.printStackTrace(); }
            this.viewModelTool.getModel().insert(jshEntity);
        }else{
            this.entity = JSHConverter.fromStringToType(jshEntities.get(0).getEntityString(), this.getEntityClass()); // jsh use only 1 entity
            this.onModelUpdate();
        }
    }
    protected abstract Class getEntityClass();
    protected abstract void onModelUpdate();
}
