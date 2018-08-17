package com.kensara.stationcarcontrol.Fragments.Administrateur;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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

public class Create_Employee_Frag extends android.support.v4.app.Fragment {

    EditText et_nom
            , et_prenom
            , et_age
            , et_tel;

    Spinner sp_type_emplole
            , sp_pompe;

    FirebaseDatabase database;

    String key;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_employee_frag, container, false);

        database = FirebaseDatabase.getInstance();

        et_nom = (EditText) view.findViewById(R.id.frag_emp_et_nom);
        et_prenom = (EditText) view.findViewById(R.id.frag_emp_et_prenom);
        et_tel = (EditText) view.findViewById(R.id.frag_emp_et_tel);
        et_age = (EditText) view.findViewById(R.id.frag_emp_et_age);

        sp_pompe = (Spinner) view.findViewById(R.id.frag_emp_spi_pompe);
        sp_type_emplole = (Spinner) view.findViewById(R.id.frag_emp_spi_type);

        setHasOptionsMenu(true);

        return view;
    }

    void saveEmploye(){
        Employe employe = new Employe();

        employe.setNom(et_nom.getText().toString());

        employe.setPrenom(et_prenom.getText().toString());

        employe.setAge(et_age.getText().toString());

        employe.setTelephone(et_tel.getText().toString());

        employe.setTypeEmploi(sp_type_emplole.getSelectedItem().toString());

        Pompe pompe = new Pompe();
        pompe.setNom(sp_pompe.getSelectedItem().toString());

        employe.setIdPompe(pompe);

        DatabaseReference refEmploye = database.getReference("Employee");

        if (key == null)
            key = refEmploye.push().getKey();

        Map<String, Object> child_rotation_Updates = new HashMap<>();
        child_rotation_Updates.put(key, employe);

        refEmploye.updateChildren(child_rotation_Updates, new DatabaseReference.CompletionListener() {
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.save_cancel_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_action:
                saveEmploye();
                break;
        }
        return true;
    }
}
