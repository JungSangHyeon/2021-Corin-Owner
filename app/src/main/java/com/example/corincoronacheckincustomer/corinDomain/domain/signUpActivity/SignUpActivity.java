package com.example.corincoronacheckincustomer.corinDomain.domain.signUpActivity;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.corincoronacheckincustomer.R;
import com.example.corincoronacheckincustomer.corinDomain.crossDomain.Constant;
import com.example.corincoronacheckincustomer.corinDomain.model.CorinEntity;
import com.example.corincoronacheckincustomer.corinDomain.model.UserEntity;
import com.example.corincoronacheckincustomer.jshCrossDomain.view.activity.JSHViewModelActivity;
import com.google.android.material.chip.ChipGroup;

public class SignUpActivity extends JSHViewModelActivity<CorinEntity> {

    // Associate
        // View
        private EditText emailEditText, passwordEditText, passwordCheckEditText, nameEditText, ageEditText;
        private ChipGroup genderChipGroup;
        private Button signUpButton;

    @Override protected int getLayoutId() { return R.layout.activity_sign_up; }

    @Override
    protected void associate() {
        View settingSignUpInfoInclude = this.findViewById(R.id.signUpActivity_include_layoutSettingSignUpInfo);
        this.emailEditText = settingSignUpInfoInclude.findViewById(R.id.settingSignUpInfoLayout_emailEditText);
        this.passwordEditText = settingSignUpInfoInclude.findViewById(R.id.settingSignUpInfoLayout_passwordEditText);
        this.passwordCheckEditText = settingSignUpInfoInclude.findViewById(R.id.settingSignUpInfoLayout_passwordCheckEditText);

        View settingMyInfoInclude = this.findViewById(R.id.signUpActivity_include_layoutEditMyInfo);
        this.nameEditText = settingMyInfoInclude.findViewById(R.id.settingPrivateInfoLayout_nameEditText);
        this.ageEditText = settingMyInfoInclude.findViewById(R.id.settingPrivateInfoLayout_ageEditText);
        this.genderChipGroup = settingMyInfoInclude.findViewById(R.id.settingPrivateInfoLayout_genderGroup);

        this.signUpButton = this.findViewById(R.id.signUpActivity_signUpButton);
    }
    @Override
    protected void initialize() {
        this.signUpButton.setOnClickListener(v->this.sighUp());
    }

    @Override protected Class getEntityClass() { return CorinEntity.class; }
    @Override protected void onModelUpdate() { }

    private void sighUp() {
        if(this.passwordEditText.getText().toString().equals(this.passwordCheckEditText.getText().toString())){
            UserEntity userEntity = new UserEntity(
                    this.emailEditText.getText().toString(),
                    this.passwordEditText.getText().toString(),
                    this.nameEditText.getText().toString(),
                    Integer.parseInt(this.ageEditText.getText().toString()),
                    Constant.GenderMap.get(this.genderChipGroup.getCheckedChipId())
            );
            this.entity.getUsers().add(userEntity);
            this.entity.setLoginUser(userEntity);
            this.entity.setLogin(true);
            Toast.makeText(this, R.string.signUpActivity_signUpComplete, Toast.LENGTH_SHORT).show();
            this.setResult(Activity.RESULT_OK);
            this.finish();
        }else{
            Toast.makeText(this, R.string.signUpActivity_passwordAndCheckDifferent, Toast.LENGTH_SHORT).show();
        }
    }
}