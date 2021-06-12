package com.example.corincoronacheckincustomer.corinDomain.view.mainActivity.myCorinFragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.corincoronacheckincustomer.R;
import com.example.corincoronacheckincustomer.corinDomain.Constant;
import com.example.corincoronacheckincustomer.corinDomain.view.editMyInfoActivity.EditMyInfoActivity;
import com.example.corincoronacheckincustomer.corinDomain.view.loginActivity.LoginActivity;
import com.example.corincoronacheckincustomer.corinDomain.view.signUpActivity.SignUpActivity;
import com.example.corincoronacheckincustomer.corinDomain.model.CorinEntity;
import com.example.corincoronacheckincustomer.corinDomain.model.UserEntity;
import com.example.corincoronacheckincustomer.jshCrossDomain.view.fragment.JSHViewModelFragment;
import com.example.corincoronacheckincustomer.jshCrossDomain.view.widget.JSHSelectItem;

import java.util.ArrayList;

public class MyCorinFragment extends JSHViewModelFragment<CorinEntity> {

    // Associate
        // View
        private ConstraintLayout loginLayout;
        private Button loginButton, signUpButton;
        private JSHSelectItem editMyInfoSelectItem;
        private ConstraintLayout logoutAndSignOutLayout;
        private TextView logoutTextView, signOutTextView;

    @Override protected int getLayoutId() { return R.layout.fragment_my_corin; }

    @Override
    protected void associate(View view) {
        this.loginLayout = view.findViewById(R.id.myCorinFragment_loginLayout);
        this.loginButton = view.findViewById(R.id.myCorinFragment_loginLayout_loginButton);
        this.signUpButton = view.findViewById(R.id.myCorinFragment_loginLayout_signUpButton);
        this.editMyInfoSelectItem = view.findViewById(R.id.myCorinFragment_editMyInfoSelectItem);
        this.logoutAndSignOutLayout = view.findViewById(R.id.myCorinFragment_logoutAndSignOutLayout);
        this.logoutTextView = view.findViewById(R.id.myCorinFragment_logoutAndSignOutLayout_logOutTextView);
        this.signOutTextView = view.findViewById(R.id.myCorinFragment_logoutAndSignOutLayout_sighOutTextView);
    }

    @Override
    protected void initialize() {
        this.loginLayout.setVisibility(View.GONE);
        this.editMyInfoSelectItem.setVisibility(View.GONE);
        this.logoutAndSignOutLayout.setVisibility(View.GONE);
        this.loginButton.setOnClickListener(v->this.getActivityResultLauncher().launch(new Intent(this.getContext(), LoginActivity.class)));
        this.signUpButton.setOnClickListener(v->this.getActivityResultLauncher().launch(new Intent(this.getContext(), SignUpActivity.class)));
        this.editMyInfoSelectItem.setOnClickListener(v->this.startEditMyInfo());
        this.logoutTextView.setOnClickListener(v->this.logout());
        this.signOutTextView.setOnClickListener(v->this.signOut());
    }

    @Override protected Class getEntityClass() { return CorinEntity.class; }
    @Override
    protected void onModelUpdate() {
        if(this.entity.isLogin()){
            this.editMyInfoSelectItem.setVisibility(View.VISIBLE);
            this.logoutAndSignOutLayout.setVisibility(View.VISIBLE);
            this.loginLayout.setVisibility(View.GONE);
        }else{
            this.editMyInfoSelectItem.setVisibility(View.GONE);
            this.logoutAndSignOutLayout.setVisibility(View.GONE);
            this.loginLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(ActivityResult result) {
        if(result.getResultCode()== Constant.EditMyInfoActivityResultCode) this.editMyInfo(result);
    }
    private void editMyInfo(ActivityResult result) { // 점수 조건인지라...
        Intent intent = result.getData();
        UserEntity userEntity = this.entity.getLoginUser();
        userEntity.setUserName(intent.getStringExtra(Constant.MyInfoTag.eName.name()));
        userEntity.setAge(intent.getIntExtra(Constant.MyInfoTag.eAge.name(), -1));
        userEntity.setGender(Constant.GenderMap.get(intent.getIntExtra(Constant.MyInfoTag.eGender.name(), -1)));
        this.deleteNowUserInfo();
        this.entity.getUsers().add(userEntity);
        Toast.makeText(this.getContext(), R.string.myCorinFragment_infoEditCompleteText, Toast.LENGTH_SHORT).show();
        this.save();
    }

    private void startEditMyInfo() {
        Intent intent = new Intent(this.getContext(), EditMyInfoActivity.class);
        intent.putExtra(Constant.MyInfoTag.eName.name(), this.entity.getLoginUser().getUserName());
        intent.putExtra(Constant.MyInfoTag.eAge.name(), this.entity.getLoginUser().getAge());
        intent.putExtra(Constant.MyInfoTag.eGender.name(), this.entity.getLoginUser().getGender().ordinal());
        this.getActivityResultLauncher().launch(intent);
    }

    private void signOut() {
        this.deleteNowUserInfo();
        this.logout();
    }

    private void logout() {
        this.entity.setLogin(false);
        this.entity.setLoginUser(null);
        this.save();
    }

    private void deleteNowUserInfo() {
        ArrayList<UserEntity> userEntities = this.entity.getUsers();
        for(int i=0; i<userEntities.size(); i++){
            if(userEntities.get(i).getId().equals(this.entity.getLoginUser().getId())) userEntities.remove(i);
        }
    }
}