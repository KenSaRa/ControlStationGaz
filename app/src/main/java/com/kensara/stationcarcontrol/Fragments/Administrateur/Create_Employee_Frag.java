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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kensara.stationcarcontrol.Model.Employe;
import com.kensara.stationcarcontrol.Model.Pompe;
import com.kensara.stationcarcontrol.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Create_Employee_Frag extends android.support.v4.app.Fragment {

    EditText et_nom, et_prenom, et_age, et_tel;


    AutoCompleteTextView act_pompe;

    FirebaseDatabase database;

    String key;

    Pompe selectedPompe;

    List<Pompe> pompes;
    ArrayAdapter<Pompe> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_employee_frag, container, false);

        getActivity().setTitle("Employé");

        database = FirebaseDatabase.getInstance();

        et_nom = (EditText) view.findViewById(R.id.frag_emp_et_nom);
        et_prenom = (EditText) view.findViewById(R.id.frag_emp_et_prenom);
        et_tel = (EditText) view.findViewById(R.id.frag_emp_et_tel);
        et_age = (EditText) view.findViewById(R.id.frag_emp_et_age);

        act_pompe = (AutoCompleteTextView) view.findViewById(R.id.frag_emp_act_pompe);
        act_pompe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedPompe = (Pompe) adapterView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        pompes = new ArrayList<>();
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, pompes);
        act_pompe.setAdapter(adapter);

        database.getReference("Pompes")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot d : dataSnapshot.getChildren()) {
                            Pompe p = d.getValue(Pompe.class);
                            if (p != null) {
                                pompes.add(p);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        setHasOptionsMenu(true);

        return view;
    }

    void saveEmploye() {
        Employe employe = new Employe();

        employe.setNom(et_nom.getText().toString());

        employe.setPrenom(et_prenom.getText().toString());

        employe.setAge(et_age.getText().toString());

        employe.setTelephone(et_tel.getText().toString());

        employe.setTypeEmploi("Employe");

        employe.setPompe(selectedPompe);

        DatabaseReference refEmploye = database.getReference("Employes");

        if (key == null)
            key = refEmploye.push().getKey();

        Map<String, Object> child_rotation_Updates = new HashMap<>();
        child_rotation_Updates.put(key, employe);

        refEmploye.updateChildren(child_rotation_Updates, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null)
                    Toast.makeText(getContext(), "Employe non ajpute", Toast.LENGTH_LONG).show();
                else {

                    Toast.makeText(getContext(), "Employe ajoute avec succes", Toast.LENGTH_LONG).show();
                    CreateEmployeListener listener = (CreateEmployeListener) getActivity();
                    listener.onCreateEmploye();
                }
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.save_cancel_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_action:
                saveEmploye();
                break;
        }
        return true;
    }

    public interface CreateEmployeListener {

        public void onCreateEmploye();


    }
}
