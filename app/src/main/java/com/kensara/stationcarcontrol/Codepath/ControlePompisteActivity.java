package com.kensara.stationcarcontrol.Codepath;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kensara.stationcarcontrol.R;
import com.kensara.stationcarcontrol.SuperAdminActivity;

public class ControlePompisteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controle_pompiste);

        Button button = findViewById(R.id.btnPompiste);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SuperAdminActivity.class));
            }
        });

    }
}
