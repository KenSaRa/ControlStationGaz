package com.kensara.stationcarcontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kensara.stationcarcontrol.Model.Rotation;
import com.kensara.stationcarcontrol.Model.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase mDatabase;

    FirebaseAuth auth;
    private static final int LOGIN_ACT = 101, SUPER_ACT=102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, LOGIN_ACT);
        }else{
            superAdminActivity();
        }

        /*
        //instancier la base
        mDatabase = FirebaseDatabase.getInstance();
        // refernece l'objet rotation
        DatabaseReference ref_Rotation = mDatabase.getReference("Rotations");
        String key = ref_Rotation.push().getKey();

        Rotation rotation = new Rotation();
        rotation.setRotationDate((new Date()).toString());
        rotation.setHeureDebut("00:00");
        rotation.setHeureFin("05:00");

        Map<String, Object> child_rotation_Updates = new HashMap<>();
        child_rotation_Updates.put(key, rotation);

        ref_Rotation.updateChildren(child_rotation_Updates);

        //reference l'objet user

        DatabaseReference ref_user = mDatabase.getReference("Rotations");
        String key_user = ref_Rotation.push().getKey();

        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");

        Map<String, Object> child_user_Updates = new HashMap<>();
        child_user_Updates.put(key, user);

        ref_user.updateChildren(child_user_Updates);
        */
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== LOGIN_ACT){
            if (resultCode == RESULT_OK)
                superAdminActivity();
            else
                finish();
        }else if(requestCode == SUPER_ACT){
            finish();
        }
    }

    void superAdminActivity(){
        Intent intent = new Intent(this, SuperAdminActivity.class);
        startActivityForResult(intent, SUPER_ACT);
    }
}
