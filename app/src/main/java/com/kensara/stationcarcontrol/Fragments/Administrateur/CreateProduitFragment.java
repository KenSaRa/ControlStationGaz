package com.kensara.stationcarcontrol.Fragments.Administrateur;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;
import com.kensara.stationcarcontrol.Model.Pompe;
import com.kensara.stationcarcontrol.R;

import java.awt.font.TextAttribute;
import java.util.List;

public class CreateProduitFragment extends Fragment
implements View.OnClickListener{

    EditText nomProdui
            ,quantite
            ,prix;
    Spinner typeProduit
            ,typePaiement;
    TextView dateCreate;

    FirebaseDatabase database;

    String key;
    Pompe selectedPompe;

    List<Pompe> pompes;
    ArrayAdapter<Pompe> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.create_produit_frag, container, false);

        database = FirebaseDatabase.getInstance();
        getActivity().setTitle("Ajouter Produit");
        return view;
    }

    @Override
    public void onClick(View view) {

    }
}
