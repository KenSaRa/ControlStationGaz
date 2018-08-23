package com.kensara.stationcarcontrol.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kensara.stationcarcontrol.R;

public class DialogSuccesFragment extends DialogFragment
implements View.OnClickListener {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.create_user_fragment, container, false);

        view.findViewById(R.id.frag_user_register).setOnClickListener(this);

        return view;
    }

    @Override    public void onClick(View view) {
        /*if (view.getId() == R.id.frag_add_depot_btn){
            DialogFragment dialog = new DialogFragment();
            dialog.show(getFragmentManager(), "SUCCES");
        }*/
    }
}
