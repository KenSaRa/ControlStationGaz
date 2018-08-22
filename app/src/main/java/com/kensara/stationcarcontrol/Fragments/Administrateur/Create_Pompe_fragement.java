package com.kensara.stationcarcontrol.Fragments.Administrateur;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kensara.stationcarcontrol.Model.Employe;
import com.kensara.stationcarcontrol.Model.Pompe;
import com.kensara.stationcarcontrol.R;

import java.util.HashMap;
import java.util.Map;

public class Create_Pompe_fragement extends Fragment {

    EditText et_nomPompe
            , et_longitude
            , et_latitude
            ,et_pays
            ,et_ville
            , et_adresse;



    FirebaseDatabase database;

    String key;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_pompe_fragement, container, false);

        getActivity().setTitle("Create employee");

        database = FirebaseDatabase.getInstance();

        //recupere valeur

        et_nomPompe = (EditText) view.findViewById(R.id.frag_pompe_et_nom);
        et_longitude = (EditText) view.findViewById(R.id.frag_pompe_et_longitue);
        et_longitude = (EditText) view.findViewById(R.id.frag_pompe_et_latitude);
        et_pays = (EditText) view.findViewById(R.id.frag_pompe_et_pays);
        et_ville = (EditText) view.findViewById(R.id.frag_pompe_et_ville);
        et_adresse = (EditText) view.findViewById(R.id.frag_pompe_et_Adresse);


        setHasOptionsMenu(true);

        return view;
    }

    void savePompe(){
        Pompe pompe = new Pompe();

        pompe.setNom(et_nomPompe.getText().toString());

       // pompe.setLongitude(et_longitude)

      //  pompe.setLatitude(et_latitude);

        pompe.setAdresse(et_adresse.getText().toString());

        pompe.setPays(et_pays.getText().toString());

        pompe.setVille(et_ville.getText().toString());



        DatabaseReference refpompe = database.getReference("Pompe");

        if (key == null)
            key = refpompe.push().getKey();

        Map<String, Object> child_rotation_Updates = new HashMap<>();
        child_rotation_Updates.put(key, pompe);

        refpompe.updateChildren(child_rotation_Updates, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null)
                    Toast.makeText(getContext(), "Employe non ajpute", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getContext(), "Employe ajoute avec succes", Toast.LENGTH_LONG).show();
            }
        });

    }


@Override
public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()){
        case R.id.save_action:
            savePompe();
            break;
    }
    return true;
}
}
