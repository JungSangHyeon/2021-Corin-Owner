package com.example.corincoronacheckincustomer.jshCrossDomain.view.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.corincoronacheckincustomer.jshCrossDomain.model.room.converter.JSHConverter;
import com.example.corincoronacheckincustomer.jshCrossDomain.model.room.entity.JSHEntity;
import com.example.corincoronacheckincustomer.jshCrossDomain.model.viewModel.JSHViewModelTool;
import com.example.corincoronacheckincustomer.jshCrossDomain.model.viewModel.ViewModelToolCallback;

import java.util.ArrayList;

/**
 * TODO 나중에 ViewModelTool 로 일부 코드 이동할 것
 */
public abstract class JSHViewModelFragment<T> extends JSHFragment implements ViewModelToolCallback {

    // Associate
        // Model
        protected T entity;

    // Component
    protected JSHViewModelTool viewModelTool;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.associate(view);
        this.initialize();

        // Create Component
        this.viewModelTool = new JSHViewModelTool(this.getActivity(), this, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.viewModelTool.startObserve();
    }
    @Override
    public void onPause() {
        super.onPause();
        this.viewModelTool.stopObserve();
//        this.save();
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
            catch (IllegalAccessException | java.lang.InstantiationException e) { e.printStackTrace(); }
            this.viewModelTool.getModel().insert(jshEntity);
        }else{
            this.entity = JSHConverter.fromStringToType(jshEntities.get(0).getEntityString(), this.getEntityClass()); // jsh use only 1 entity
            this.onModelUpdate();
        }
    }
    protected abstract Class getEntityClass();
    protected abstract void onModelUpdate();
}
