package com.kensara.stationcarcontrol.Fragments.Administrateur;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kensara.stationcarcontrol.Model.Employe;
import com.kensara.stationcarcontrol.Model.Pompe;
import com.kensara.stationcarcontrol.R;

import java.util.HashMap;
import java.util.Map;

public class Create_Pompe_fragement extends Fragment {

    EditText et_nomPompe, et_longitude, et_latitude, et_pays, et_ville, et_adresse;


    FirebaseDatabase database;

    String key;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_pompe_fragement, container, false);

        getActivity().setTitle("Ajouter pompe");

        database = FirebaseDatabase.getInstance();

        //recupere valeur

        et_nomPompe = (EditText) view.findViewById(R.id.etNom);
        et_longitude = (EditText) view.findViewById(R.id.etLontitude);
        et_latitude = (EditText) view.findViewById(R.id.etLatitude);
        et_pays = (EditText) view.findViewById(R.id.etPays);
        et_ville = (EditText) view.findViewById(R.id.etVille);
        et_adresse = (EditText) view.findViewById(R.id.etAdresse);

        Button button = view.findViewById(R.id.frag_btnPompe);

        setHasOptionsMenu(true);

        return view;
    }

    void savePompe() {
        Pompe pompe = new Pompe();

        pompe.setNom(et_nomPompe.getText().toString());

        try {
            pompe.setLongitude(Double.parseDouble(et_longitude.getText().toString()));

            pompe.setLatitude(Double.parseDouble(et_latitude.getText().toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        pompe.setAdresse(et_adresse.getText().toString());

        pompe.setPays(et_pays.getText().toString());

        pompe.setVille(et_ville.getText().toString());


        DatabaseReference refpompe = database.getReference("/Pompes/");

        if (key == null)
            key = refpompe.push().getKey();

        Log.v("Create pompe", key);

        Map<String, Object> child_rotation_Updates = new HashMap<>();
        child_rotation_Updates.put( key, pompe);

        refpompe.updateChildren(child_rotation_Updates)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Employe ajoute avec succes", Toast.LENGTH_LONG).show();
                        Log.v("Create pompe", "Employe ajoute avec succes");
                        CreatePompeListener listener= (CreatePompeListener) getActivity();
                        listener.onPompeCreated();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Employe non ajpute", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.save_cancel_menu, menu);
    }
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_action:
                savePompe();
                break;
        }
        return true;
    }*/

    public interface CreatePompeListener{
        public void onPompeCreated();
    }
}
