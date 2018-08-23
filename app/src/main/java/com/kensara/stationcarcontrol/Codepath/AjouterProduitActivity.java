package com.kensara.stationcarcontrol.Codepath;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.kensara.stationcarcontrol.R;

import java.util.ArrayList;
import java.util.List;

public class AjouterProduitActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_produit);

        Button button = findViewById(R.id.btnProduit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ControlePompisteActivity.class));
            }
        });


        //Spinner
        Spinner spinner = (Spinner) findViewById(R.id.etTypeProduit);
        spinner.setOnItemSelectedListener(this);


        // Spinner Drop down elements
        List<String> listeProduit = new ArrayList<String>();
        listeProduit.add("Type de produit");
        listeProduit.add("Diesel");
        listeProduit.add("Gazoline");


        // Creating adapter for the spinner
        ArrayAdapter<String> ProduitAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, listeProduit);

        // Drop down layout style - list view with radio button
        ProduitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(ProduitAdapter);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
