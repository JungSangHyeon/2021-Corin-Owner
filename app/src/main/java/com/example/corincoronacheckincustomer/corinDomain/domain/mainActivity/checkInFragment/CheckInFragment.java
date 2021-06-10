package com.example.corincoronacheckincustomer.corinDomain.domain.mainActivity.checkInFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.corincoronacheckincustomer.R;

public class CheckInFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_check_in, container, false);
    }
}