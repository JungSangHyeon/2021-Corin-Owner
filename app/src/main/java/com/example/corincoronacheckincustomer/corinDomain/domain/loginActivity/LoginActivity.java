package com.example.corincoronacheckincustomer.corinDomain.domain.loginActivity;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;

import com.example.corincoronacheckincustomer.R;
import com.example.corincoronacheckincustomer.corinDomain.domain.signUpActivity.SignUpActivity;
import com.example.corincoronacheckincustomer.corinDomain.model.CorinEntity;
import com.example.corincoronacheckincustomer.corinDomain.model.UserEntity;
import com.example.corincoronacheckincustomer.jshCrossDomain.view.activity.JSHViewModelActivity;

public class LoginActivity extends JSHViewModelActivity<CorinEntity> {

    // Associate
        // View
        private EditText emailEditText, pwEditText;
        private Button loginButton;
        private TextView signUpTextView;

    @Override protected int getLayoutId() { return R.layout.activity_login; }

    @Override
    protected void associate() {
        // View
        this.emailEditText = this.findViewById(R.id.loginActivity_emailEditText);
        this.pwEditText = this.findViewById(R.id.loginActivity_passwordEditText);
        this.loginButton = this.findViewById(R.id.loginActivity_loginButton);
        this.signUpTextView = this.findViewById(R.id.loginActivity_signUpTextView);
    }
    @Override
    protected void initialize() {
        // View
        this.loginButton.setOnClickListener(v->this.login());
        this.signUpTextView.setOnClickListener(v->this.getActivityResultLauncher().launch(new Intent(this, SignUpActivity.class)));
    }

    @Override protected Class getEntityClass() { return CorinEntity.class; }
    @Override protected void onModelUpdate() { }

    @Override
    protected void onActivityResult(ActivityResult result) {
        if(result.getResultCode()== Activity.RESULT_OK) this.finish();
    }

    private void login() {
        for(UserEntity userEntity : this.entity.getUsers()){
            if(userEntity.getId().equals(this.emailEditText.getText().toString()) && userEntity.getPw().equals(this.pwEditText.getText().toString())){
                this.entity.setLogin(true);
                this.entity.setLoginUser(userEntity);
                return;
            }
        }
        Toast.makeText(this, R.string.loginActivity_noMatchUserText, Toast.LENGTH_SHORT).show();
    }
}