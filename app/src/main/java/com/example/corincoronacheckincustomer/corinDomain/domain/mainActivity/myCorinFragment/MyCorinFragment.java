package com.example.corincoronacheckincustomer.corinDomain.domain.mainActivity.myCorinFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.corincoronacheckincustomer.R;
import com.example.corincoronacheckincustomer.corinDomain.domain.loginActivity.LoginActivity;
import com.example.corincoronacheckincustomer.corinDomain.domain.signUpActivity.SignUpActivity;

public class MyCorinFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_corin, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button loginButton = view.findViewById(R.id.myCorinFragment_loginLayout_loginButton);
        Button signUpButton = view.findViewById(R.id.myCorinFragment_loginLayout_signUpButton);

        loginButton.setOnClickListener(v->this.getActivity().startActivity(new Intent(this.getContext(), LoginActivity.class)));
        signUpButton.setOnClickListener(v->this.getActivity().startActivity(new Intent(this.getContext(), SignUpActivity.class)));
    }
}