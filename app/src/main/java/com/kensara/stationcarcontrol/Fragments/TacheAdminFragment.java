package com.kensara.stationcarcontrol.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kensara.stationcarcontrol.R;

public class TacheAdminFragment extends Fragment{


        @Nullable
        @Override
        public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup
        container, @Nullable Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.frag_bienvenu_admin, container, false);

       // view.findViewById(R.id.frag_add_depot_btn).setOnClickListener(this);

        return view;
    }





}
