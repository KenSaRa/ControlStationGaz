package com.kensara.stationcarcontrol.Fragments.Administrateur;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kensara.stationcarcontrol.R;

public class List_employe_Frag extends Fragment{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_employe_frag, container, false);

        return view;
    }
}
