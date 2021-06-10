package com.example.corincoronacheckincustomer.corinDomain.domain.mainActivity.checkInFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.corincoronacheckincustomer.R;
import com.example.corincoronacheckincustomer.corinDomain.domain.loginActivity.LoginActivity;

import org.jetbrains.annotations.NotNull;

public class CheckInFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_check_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button b = view.findViewById(R.id.checkInFragment_loginButton);

        // launcher 선언
        ActivityResultLauncher<Intent> launcher;
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Log.d("TESTTEST", "FRAGMENT ACTIVITY RESULT");
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();

                        // RESULT_OK일 때 실행할 코드...
                    }
                });

        b.setOnClickListener(v->{
            Intent intent = new Intent(this.getActivity(), LoginActivity.class);
            launcher.launch(intent);
        });

    }

}