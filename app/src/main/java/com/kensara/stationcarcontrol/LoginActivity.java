package com.kensara.stationcarcontrol;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.kensara.stationcarcontrol.Model.User;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity
implements View.OnClickListener {

    private static final String TAG = "LoginActivity";


    FirebaseAuth auth;

    private EditText edt_email, edt_password;
    private TextView tv_error;
    private Button btn_connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        edt_email = (EditText) findViewById(R.id.edt_username);
        edt_password = (EditText) findViewById(R.id.edt_password);

        tv_error = (TextView) findViewById(R.id.tvError);

        btn_connect = (Button) findViewById(R.id.btn_connexion);
        btn_connect.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_connexion:
                String email = edt_email.getText().toString();
                String password = edt_password.getText().toString();

                if (email.length() == 0)
                    edt_email.setError("Email empty");

                if (password.length() == 0)
                    edt_password.setError("Password empty");

                if (email.length() > 0 && password.length() > 0){
                    auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInWithEmail:success");
                                        FirebaseUser user = auth.getCurrentUser();
                                       authSuccess();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                        tv_error.setText(task.getResult().toString());

                                    }

                                }
                            });
                }


                break;
        }


    }

    private void authSuccess(){
        setResult(RESULT_OK);
        finish();
    }
}
